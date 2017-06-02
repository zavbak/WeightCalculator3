package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.barcode;

import io.realm.Realm;
import io.realm.RealmModel;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class SaveBarcodeInteractor extends BaseInteractor {


    Realm realm = Realm.getDefaultInstance();
    Barcode mBarcode;
    long mIdProd;


    public SaveBarcodeInteractor(Barcode barcode,long idProd) {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());
        mBarcode = barcode;
        mIdProd = idProd;
    }



    private  <E extends RealmModel> long getNextId(Class<E> clazz) {

        long id = 1;

        try {
            id = realm.where(Barcode.class).max("id").intValue() + 1;
        } catch (Exception e) {

        }

        return id;
    }

    @Override
    protected Observable<Barcode> getObservable() {

        try {
            realm.beginTransaction();

            Boolean isNew = (mBarcode.getId()==0)?true:false;

            if (mBarcode.getId() == 0) {
                mBarcode.setId(getNextId(Barcode.class));
            }

            Barcode realmBarcode = realm.copyToRealmOrUpdate(mBarcode);

            if(isNew){
                Product product = realm.where(Product.class).equalTo("id", mIdProd).findFirst();
                product.getBarcodes().add(realmBarcode);
            }

            realm.commitTransaction();

            return Observable.just(realmBarcode);
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return Observable.error(e);

        }
    }
}
