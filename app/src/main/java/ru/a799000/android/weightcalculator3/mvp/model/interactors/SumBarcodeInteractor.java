package ru.a799000.android.weightcalculator3.mvp.model.interactors;

import java.math.BigDecimal;

import javax.inject.Inject;

import io.realm.RealmList;
import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;


/**
 * Created by Alex on 24.05.2017.
 */

public class SumBarcodeInteractor {

    @Inject
    BarcodeInteractor mBarcodeInteractor;

    @Inject
    public SumBarcodeInteractor() {
        App.getAppComponent().injectSumBarcodeInteractor(this);
    }

    public SumBarcodeInfo getSumBarcodeInfo(long idProdact) {

        BigDecimal sumWeight = new BigDecimal("0");
        BigDecimal sumPleces = new BigDecimal("0");

        SumBarcodeInfo sumBarcodeInfo = new SumBarcodeInfo();

        RealmList<Barcode> barcodes = mBarcodeInteractor.getBarcodesByIdProduct(idProdact);

        if(barcodes != null){
            for (Barcode bar : barcodes) {
                sumWeight =  sumWeight.add(new BigDecimal(Float.toString(bar.getWeight())));
                sumPleces =  sumPleces.add(new BigDecimal(Float.toString(bar.getPlaces())));
            }
        }


        sumBarcodeInfo.setSumWeight(sumWeight.toString());
        sumBarcodeInfo.setSumPlaces(sumPleces.toString());


        return sumBarcodeInfo;


    }


    public class SumBarcodeInfo{

        String sumWeight;
        String sumPlaces;

        public void setSumWeight(String sumWeight) {
            this.sumWeight = sumWeight;
        }

        public void setSumPlaces(String sumPlaces) {
            this.sumPlaces = sumPlaces;
        }

        public String getSumWeight() {
            return sumWeight;
        }

        public String getSumPlaces() {
            return sumPlaces;
        }
    }

}
