package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public abstract class BaseInteractor<T> {

    private Scheduler mJobScheduler;
    private Scheduler mUiScheduler;

    private Subscription subscription = Subscriptions.empty();

    public BaseInteractor(Scheduler jobScheduler, Scheduler uiScheduler) {
        mJobScheduler = jobScheduler;
        mUiScheduler  = uiScheduler;
    }

    protected abstract Observable<T> getObservable();

    public Subscription execute(Subscriber<T> subscriber) {
        return subscription = getObservable()
                .subscribeOn(mJobScheduler) //Schedulers.io()
                .observeOn(mUiScheduler) //AndroidSchedulers.mainThread()
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }

}
