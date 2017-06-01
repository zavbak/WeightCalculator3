package ru.a799000.android.weightcalculator3.repository.realm.repository.impl;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.repository.realm.init.RealmTable;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IBarcodeRepository;


/**
 * Created by Alex on 22.05.2017.
 */

public class BarcodeRepository implements IBarcodeRepository {

    Realm mRealm;

    public BarcodeRepository() {
        mRealm = Realm.getDefaultInstance();
    }

    public <E extends RealmModel> long getNextId(Class<E> clazz) {

        long id = 1;

        try {
            id = mRealm.where(Barcode.class).max(RealmTable.ID).intValue() + 1;
        } catch (Exception e) {

        }

        return id;
    }

    @Override
    public void saveBarcode(Barcode barcode, OnSaveBarcodeCallback callback) {

        mRealm.beginTransaction();
        try {
            if (barcode.getId() == 0) {
                barcode.setId(getNextId(Product.class));
            }
            Barcode barcodeModel = mRealm.copyToRealmOrUpdate(barcode);
            mRealm.commitTransaction();
            if (callback != null)
                callback.onSuccess(barcodeModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
            if (callback != null)
                callback.onError(e.getMessage());
            mRealm.cancelTransaction();
        }


    }

    @Override
    public void saveBarcodeByProduct(Barcode barcode, long productId, OnSaveBarcodeCallback callback) {

        mRealm.beginTransaction();
        try {
            Boolean isNew = false;

            if(barcode.getId()==0)
                isNew = true;


            if (barcode.getId() == 0) {
                barcode.setId(getNextId(Product.class));
            }
            Barcode realmBarcode = mRealm.copyToRealmOrUpdate(barcode);

            if(isNew){
                Product product = mRealm.where(Product.class).equalTo(RealmTable.ID, productId).findFirst();
                product.getBarcodes().add(realmBarcode);
            }

            mRealm.commitTransaction();

            if (callback != null)
                callback.onSuccess(realmBarcode.getId());

        } catch (Exception e) {
            e.printStackTrace();
            if (callback != null)
                callback.onError(e.getMessage());
            mRealm.cancelTransaction();
        }


    }

    @Override
    public void deleteBarcodeById(long id, OnDeleteBarcodeCallback callback) {
        mRealm.beginTransaction();

        try {
            Barcode barcode = mRealm.where(Barcode.class).equalTo(RealmTable.ID, id).findFirst();
            barcode.deleteFromRealm();
            mRealm.commitTransaction();
            if (callback != null)
                callback.onSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            if (callback != null)
                callback.onError(e.getMessage());
            mRealm.cancelTransaction();
        }
    }



    @Override
    public void getAllBarcodes(OnGetAllBarcodesCallback callback) {

        RealmQuery<Barcode> query = mRealm.where(Barcode.class);
        RealmResults<Barcode> results = query.findAll();

        if (callback != null)
            callback.onSuccess(results);

    }

    @Override
    public void getAllBarcodesByProductId(long id, OnGetBarcodesCallback callback) {

        Product product = mRealm.where(Product.class).equalTo(RealmTable.ID, id).findFirst();

        if (product == null) {
            if (callback != null)
                callback.onSuccess(null);
        } else {
            RealmList<Barcode> barcodes = product.getBarcodes();

            if (callback != null)
                callback.onSuccess(barcodes);
        }

    }

    @Override
    public void getBarcodeById(long id, OnGetBarcodeByIdCallback callback) {
        Barcode barcode = mRealm.where(Barcode.class).equalTo(RealmTable.ID, id).findFirst();

        if (callback != null)
            callback.onSuccess(barcode);

    }
}
