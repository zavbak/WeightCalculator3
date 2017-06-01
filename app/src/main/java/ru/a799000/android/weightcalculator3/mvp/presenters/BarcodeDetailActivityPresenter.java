package ru.a799000.android.weightcalculator3.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.model.interactor.BarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactor.BarcodeSeporatorIterator;
import ru.a799000.android.weightcalculator3.mvp.model.interactor.ProductInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.mvp.view.BarcodeDetailActivityView;


/**
 * Created by Alex on 23.05.2017.
 */
@InjectViewState
public class BarcodeDetailActivityPresenter extends MvpPresenter<BarcodeDetailActivityView> {
    Barcode mBarcode;
    Product mProduct;

    @Inject
    BarcodeInteractor mBarcodeInteractor;

    @Inject
    ProductInteractor mProductInteractor;



    public BarcodeDetailActivityPresenter() {
        App.getAppComponent().injectBarcodeDetailActivityPresenter(this);
    }

    public String getId() {
        return Long.toString(mBarcode.getId());
    }

    public String getBarcode() {
        return mBarcode.getBarcode();
    }

    public String getPlaces() {
        return Integer.toString(mBarcode.getPlaces());
    }

    public String getWeight() {
        return Float.toString(mBarcode.getWeight());
    }

    public void setBarcode(String id,String barcode) {
       if(id == null){
           if(mBarcode == null){
               mBarcode = new Barcode();
               mBarcode.setBarcode(barcode);
               mBarcode.setPlaces(1);

               BarcodeSeporatorIterator mSeporatorIterator= new BarcodeSeporatorIterator(barcode,mProduct);
               mBarcode.setWeight(mSeporatorIterator.getWeight());
           }
       }else{

           mBarcode = mBarcodeInteractor.getBarcode(Long.parseLong(id));

       }

    }

    public void setProduct(String id) {
        if(id == null){
            if(mProduct == null){
                mProduct = new Product();
            }
        }else{

            mProduct = mProductInteractor.getProduct(Long.parseLong(id));
        }
    }

    public void refresh() {
        getViewState().refreshView();
    }

    public void save(String barcode, String weight, String places) {
        mBarcode.setBarcode(barcode);
        mBarcode.setWeight(Float.parseFloat(weight));
        mBarcode.setPlaces(Integer.parseInt(places));
        mBarcodeInteractor.saveBarcode(mBarcode,mProduct.getId());
        getViewState().finishView();
    }

    public void clickDell() {
       mBarcodeInteractor.dellBarcode(mBarcode);
        getViewState().finishView();

    }

    public void btCancel() {
        getViewState().finishView();
    }
}
