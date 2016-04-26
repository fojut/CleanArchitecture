package org.fojut.sample.data.service;

import org.fojut.sample.data.application.ApplicationTestCase;
import org.fojut.sample.data.user.dto.UserResponseDto;
import org.fojut.sample.data.user.service.UserApiService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;



import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;


public class UserApiServiceTest extends ApplicationTestCase {

    private static final String TAG = UserApiServiceTest.class.getSimpleName();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsersCase(){
        Observable<UserResponseDto> userEntityObservable = UserApiService.getInstance().getUserList();
        Assert.assertNotNull(userEntityObservable);

        Subscription subscription = userEntityObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<UserResponseDto>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error => "+e.getMessage());
                    }

                    @Override
                    public void onNext(UserResponseDto userResponseEntity) {
                        System.out.println("OnNext code => "+userResponseEntity.getCode()+", OnNext size => "+userResponseEntity.getData().getList().size()
                                +", list => "+userResponseEntity.getData().getList().toString());
                    }
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subscription.unsubscribe();
    }

}
