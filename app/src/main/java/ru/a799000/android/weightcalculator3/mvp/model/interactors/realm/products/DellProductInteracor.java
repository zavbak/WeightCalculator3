package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products;

import io.realm.Realm;

import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class DellProductInteracor extends BaseInteractor {
    Realm realm = Realm.getDefaultInstance();
    long mid;

    public DellProductInteracor(long id) {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());
        mid = id;
    }

    @Override
    protected Observable getObservable() {


        try {
            realm.beginTransaction();
            Product product = realm.where(Product.class).equalTo("id", mid).findFirst();
            product.deleteFromRealm();
            realm.commitTransaction();
            return Observable.empty();

        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();

            return Observable.error(e);

        }
    }
}
