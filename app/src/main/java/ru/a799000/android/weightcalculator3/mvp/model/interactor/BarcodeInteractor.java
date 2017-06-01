package ru.a799000.android.weightcalculator3.mvp.model.interactor;

import javax.inject.Inject;

import io.realm.RealmList;
import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IBarcodeRepository;


/**
 * Created by Alex on 24.05.2017.
 */

public class BarcodeInteractor {

    @Inject
    IBarcodeRepository mBarcodeRepository;

    RealmList<Barcode> mBarcodes;




    @Inject
    public BarcodeInteractor() {
        App.getAppComponent().injectBarcodeInteractor(this);
    }

    public RealmList<Barcode> getBarcodesByIdProduct(long idProducts){

        mBarcodes = null;

        mBarcodeRepository.getAllBarcodesByProductId(idProducts, new IBarcodeRepository.OnGetBarcodesCallback() {
            @Override
            public void onSuccess(RealmList<Barcode> barcodes) {
                mBarcodes = barcodes;
            }

            @Override
            public void onError(String message) {
                mBarcodes = null;
            }
        });

        return mBarcodes;

    }




    public Barcode getBarcode(long idBarcode){

        Barcode barcode = new Barcode();

        mBarcodeRepository.getBarcodeById(idBarcode, new IBarcodeRepository.OnGetBarcodeByIdCallback() {
            @Override
            public void onSuccess(Barcode barcodeRealm) {
                barcode.setId(barcodeRealm.getId());
                barcode.setBarcode(barcodeRealm.getBarcode());
                barcode.setWeight(barcodeRealm.getWeight());
                barcode.setPlaces(barcodeRealm.getPlaces());
            }

            @Override
            public void onError(String message) {

            }
        });

        return barcode;
    }


    public void dellBarcode(Barcode barcode){
        if(barcode.getId() != 0){
            mBarcodeRepository.deleteBarcodeById(barcode.getId(),null);
        }
    }

    public void saveBarcode(Barcode barcode,long idProduct){
        mBarcodeRepository.saveBarcodeByProduct(barcode,idProduct,null);
    }
}
