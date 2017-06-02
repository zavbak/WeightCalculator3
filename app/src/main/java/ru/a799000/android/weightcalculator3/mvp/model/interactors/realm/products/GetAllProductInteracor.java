package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class GetAllProductInteracor extends BaseInteractor {
    Realm realm = Realm.getDefaultInstance();


    public GetAllProductInteracor() {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());

    }

    @Override
    protected Observable<RealmResults<Product>> getObservable() {


        try {
            RealmQuery<Product> query = realm.where(Product.class);
            RealmResults<Product> results = query.findAll();


            return Observable.just(results);

        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();

            return Observable.error(e);

        }
    }
}
