package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products;

import io.realm.Realm;

import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class GetProductByIdInteracor extends BaseInteractor {
    Realm realm = Realm.getDefaultInstance();
    long mId;

    public GetProductByIdInteracor(long id) {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());
        mId = id;
    }

    @Override
    protected Observable<Product> getObservable() {
        try {
            Product results = realm.where(Product.class).equalTo("id", mId).findFirst();
            return Observable.just(results);

        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return Observable.error(e);
        }
    }
}
