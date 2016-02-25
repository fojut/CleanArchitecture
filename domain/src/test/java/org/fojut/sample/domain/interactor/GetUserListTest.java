package org.fojut.sample.domain.interactor;

import org.fojut.sample.domain.executor.PostExecutionThread;
import org.fojut.sample.domain.executor.ThreadExecutor;
import org.fojut.sample.domain.interactor.base.DefaultSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetUserListTest {

    private static final String TAG = GetUserListTest.class.getSimpleName();

    private GetUserListUseCase getUserListUseCase;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.getUserListUseCase = new GetUserListUseCase(mockThreadExecutor, mockPostExecutionThread){

            @Override
            protected Observable buildUseCaseObservable() {
                String[] arrays = {"peter", "fojut", "sam"};
                return Observable.from(arrays);
            }
        };
    }

    @Test
    public void testGetUserListUseCaseObservable(){
        given(mockPostExecutionThread.getScheduler()).willReturn(Schedulers.newThread());

        Subscription subscription = getUserListUseCase.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(mockPostExecutionThread.getScheduler())
                .subscribe(new TestSubscriber<String>());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.unsubscribe();

    }

    @Test
    public void testSubscribe(){
        Subscription subscription = Observable.just("Hi, fojut")
                .subscribe(new TestSubscriber<String>());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.unsubscribe();
    }


    public class TestSubscriber<T> extends DefaultSubscriber<T>{
        @Override
        public void onCompleted() {
            System.out.println("Completed!");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Error => "+e.getMessage());
        }

        @Override
        public void onNext(T t) {
            System.out.println("OnNext => "+t);
        }
    }
}
