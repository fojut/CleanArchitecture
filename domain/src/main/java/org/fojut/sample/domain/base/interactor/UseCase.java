package org.fojut.sample.domain.base.interactor;

import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

    protected final ThreadExecutor threadExecutor;
    protected final PostExecutionThread postExecutionThread;

    protected Subscription subscription = Subscriptions.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable(Object ...params);

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable)}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber, Object ...params) {
        this.subscription = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    /**
     * Executes the current db use case. Only for StorIOSQLite db operation
     * StorIOSQLite is used Scheduler.io as default, so no need to set subscribeOn scheduler.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable)}.
     */
    public void executeDb(Subscriber useCaseSubscriber, Object ...params){
        this.subscription = this.buildUseCaseObservable(params)
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
