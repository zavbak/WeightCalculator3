package ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.barcode;

import io.realm.Realm;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.realm.BaseInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Alex on 02.06.2017.
 */

public class DellAllBarcodeInteractor extends BaseInteractor {


    Realm realm = Realm.getDefaultInstance();



    public DellAllBarcodeInteractor() {
        super(AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread());

    }

    @Override
    protected Observable<Barcode> getObservable() {

        try {
            realm.beginTransaction();

            Barcode barcode = realm.where(Barcode.class).findFirst();
            barcode.deleteFromRealm();

            realm.commitTransaction();

            return Observable.empty();
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return Observable.error(e);

        }
    }
}
