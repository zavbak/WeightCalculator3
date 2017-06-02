package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.products;

import io.realm.Realm;
import io.realm.RealmModel;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class SaveProductInteractor extends BaseInteractor {


    Realm realm = Realm.getDefaultInstance();
    Product mProduct;

    public SaveProductInteractor(Product product) {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());
        mProduct = product;
    }



    private  <E extends RealmModel> long getNextId(Class<E> clazz) {

        long id = 1;

        try {
            id = realm.where(Product.class).max("id").intValue() + 1;
        } catch (Exception e) {

        }

        return id;
    }

    @Override
    protected Observable<Product> getObservable() {


        try {
            realm.beginTransaction();
            if (mProduct.getId() == 0) {
                mProduct.setId(getNextId(Product.class));
            }
            Product realmProduct = realm.copyToRealmOrUpdate(mProduct);
            realm.commitTransaction();

            return Observable.just(realmProduct);
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return Observable.error(e);

        }
    }
}
