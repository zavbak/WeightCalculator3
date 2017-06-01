package ru.a799000.android.weightcalculator3.repository.realm.repository.impl;


import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.repository.realm.init.RealmTable;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IProductRepository;


/**
 * Created by roma on 16.10.15.
 */
public class ProductRepository implements IProductRepository {

    Realm mRealm;

    public ProductRepository() {
        mRealm = Realm.getDefaultInstance();
    }

    public <E extends RealmModel> long getNextId(Class<E> clazz) {

        long id = 1;

        try {
            id = mRealm.where(Product.class).max(RealmTable.ID).intValue() + 1;
        } catch (Exception e) {

        }

        return id;
    }

    @Override
    public void saveProduct(Product product, @NonNull OnAddProductCallback callback) {

        mRealm.beginTransaction();

        try {
            if (product.getId() == 0) {
                product.setId(getNextId(Product.class));
            }
            Product realmProduct = mRealm.copyToRealmOrUpdate(product);
            mRealm.commitTransaction();
            if (callback != null)
                callback.onSuccess(realmProduct.getId());
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            if (callback != null)
                callback.onError(e.getMessage());
        }


    }


    @Override
    public void deleteProductById(long Id, OnDeleteProductCallback callback) {

        mRealm.beginTransaction();

        try {
            Product product = mRealm.where(Product.class).equalTo(RealmTable.ID, Id).findFirst();
            product.deleteFromRealm();
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
    public void deleteProductAll(OnDeleteProductCallback callback) {
        mRealm.beginTransaction();

        try {
            RealmResults<Product> results = mRealm.where(Product.class).findAll();
            results.deleteAllFromRealm();
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
    public void getAllProduct(OnGetAllProductCallback callback) {

        RealmQuery<Product> query = mRealm.where(Product.class);
        RealmResults<Product> results = query.findAll();

        if (callback != null)
            callback.onSuccess(results);

    }

    @Override
    public void getProductById(long id, OnGetProductByIdCallback callback) {


        Product result = mRealm.where(Product.class).equalTo(RealmTable.ID, id).findFirst();

        if (callback != null)
            callback.onSuccess(result);

    }
}
