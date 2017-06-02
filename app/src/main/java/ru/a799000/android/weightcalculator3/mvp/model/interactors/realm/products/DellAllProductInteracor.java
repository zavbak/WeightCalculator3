package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class DellAllProductInteracor extends BaseInteractor {
    Realm realm = Realm.getDefaultInstance();


    public DellAllProductInteracor() {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());
    }

    @Override
    protected Observable getObservable() {


        try {
            realm.beginTransaction();
            RealmResults<Product> results = realm.where(Product.class).findAll();
            results.deleteAllFromRealm();

            realm.commitTransaction();

            return Observable.empty();

        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();

            return Observable.error(e);

        }
    }
}
