package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.barcode;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class GetAllBarcodeInteracor extends BaseInteractor {
    Realm realm = Realm.getDefaultInstance();
    long mIdProd;


    public GetAllBarcodeInteracor(long idProd) {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());
        mIdProd = idProd;

    }

    @Override
    protected Observable getObservable() {

        try {

            if (mIdProd != 0) {
                //Product product = realm.where(Product.class).equalTo("id", mIdProd).findFirst();

                RealmResults<Barcode> results = realm.where(Barcode.class).equalTo("product.id", mIdProd).findAll();
                return Observable.just(results);
            } else {
                RealmQuery<Barcode> query = realm.where(Barcode.class);
                RealmResults<Barcode> results = query.findAll();
                return Observable.just(results);
            }

        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();

            return Observable.error(e);

        }
    }
}
