package ru.a799000.android.weightcalculator3.repository.realm.repository;



import io.realm.RealmList;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;


/**
 * Created by roma on 16.10.15.
 */
public interface IBarcodeRepository {

    interface OnSaveBarcodeCallback {
        void onSuccess(long id);
        void onError(String message);
    }

    interface OnDeleteBarcodeCallback {
        void onSuccess();
        void onError(String message);
    }

    interface OnGetBarcodeByIdCallback {
        void onSuccess(Barcode barcode);
        void onError(String message);
    }

    interface OnGetAllBarcodesCallback {
        void onSuccess(RealmResults<Barcode> barcodes);
        void onError(String message);
    }

    interface OnGetBarcodesCallback{
        void onSuccess(RealmList<Barcode> barcodes);
        void onError(String message);
    }

    void saveBarcode(Barcode barcode, OnSaveBarcodeCallback callback);

    void saveBarcodeByProduct(Barcode barcode, long productId, OnSaveBarcodeCallback callback);

    void deleteBarcodeById(long id, OnDeleteBarcodeCallback callback);

    void getAllBarcodes(OnGetAllBarcodesCallback callback);

    void getAllBarcodesByProductId(long id, OnGetBarcodesCallback callback);

    void getBarcodeById(long id, OnGetBarcodeByIdCallback callback);

}
