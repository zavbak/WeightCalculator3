package ru.a799000.android.weightcalculator3.repository.realm.repository;


import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;


/**
 * Created by roma on 16.10.15.
 */
public interface IProductRepository {

    interface OnAddProductCallback {
        void onSuccess(long id);
        void onError(String message);
    }

    interface OnGetAllProductCallback {
        void onSuccess(RealmResults<Product> products);
        void onError(String message);
    }

    interface OnGetProductByIdCallback {
        void onSuccess(Product Product);
        void onError(String message);
    }

    interface OnDeleteProductCallback {
        void onSuccess();
        void onError(String message);
    }

    void saveProduct(Product Product, OnAddProductCallback callback);
    void deleteProductById(long Id, OnDeleteProductCallback callback);
    void deleteProductAll(OnDeleteProductCallback callback);
    void getAllProduct(OnGetAllProductCallback callback);
    void getProductById(long id, OnGetProductByIdCallback callback);
}
