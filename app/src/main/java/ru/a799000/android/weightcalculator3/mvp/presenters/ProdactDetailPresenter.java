package ru.a799000.android.weightcalculator3.mvp.presenters;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.a799000.android.weightcalculator3.app.App;

import ru.a799000.android.weightcalculator3.mvp.model.interactors.ProductInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.BarcodeSeporatorIterator;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.SumBarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
import ru.a799000.android.weightcalculator3.mvp.view.ProdactDetailView;


/**
 * Created by Alex on 23.05.2017.
 */

@InjectViewState
public class ProdactDetailPresenter extends MvpPresenter<ProdactDetailView> {

    Product mProduct;

    @Inject
    ProductInteractor mProductInteractor;

    @Inject
    SumBarcodeInteractor mSumBarcodeInteractor;

    BarcodeSeporatorIterator mSeporatorIterator;
    SumBarcodeInteractor.SumBarcodeInfo mSumBarcodeInfo;

    public ProdactDetailPresenter() {
        App.getAppComponent().injectProdactDetailPresenter(this);
    }

    public String getId() {
        return Long.toString(mProduct.getId());
    }

    public CharSequence getBarcode() {

        if(mSeporatorIterator.getError() ){
            return mProduct.getInitBarcode();
        }

        final SpannableStringBuilder text = new SpannableStringBuilder(mProduct.getInitBarcode());
        final ForegroundColorSpan style = new ForegroundColorSpan(Color.rgb(255, 0, 0));
        text.setSpan(style, mProduct.getStart()-1, mProduct.getFinish(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return text;
    }

    public String getName() {
        return mProduct.getName();
    }

    public String getStart() {
        return Integer.toString(mProduct.getStart());
    }

    public String getFinish() {
        return Integer.toString(mProduct.getFinish());
    }

    public String getCof() {
        return Float.toString(mProduct.getCoef());
    }

    public void setProduct(String id) {

        if (id == null) {
            if (mProduct == null) {
                mProduct = new Product();
            }
        } else {
            mProduct = mProductInteractor.getProduct(Long.parseLong(id));
        }

    }


    public void refresh(){
        mSeporatorIterator = new BarcodeSeporatorIterator(mProduct.getInitBarcode(),mProduct);
        getViewState().refreshView();
    }

    public void onStart() {
        mSumBarcodeInfo = mSumBarcodeInteractor.getSumBarcodeInfo(mProduct.getId());
        refresh();
        getViewState().registerBarcodeReceiver();
    }

    public void onStop(){
        getViewState().unregisterReceiver();
    }


    public void save(String barcode, String name, String start, String finish, String coef) {

        mProduct.setInitBarcode(barcode);
        mProduct.setName(name);
        mProduct.setStart(Integer.parseInt(start));
        mProduct.setFinish(Integer.parseInt(finish));
        mProduct.setCoef(Float.parseFloat(coef));

        mProductInteractor.saveProduct(mProduct);

        getViewState().finishView();

    }

    public void onClickDell() {
        mProductInteractor.dellProduct(mProduct);
        getViewState().finishView();
    }

    public void onClickBarcodes() {
        if (mProduct.getId() != 0) {
            getViewState().startBarcodesActivityView(Long.toString(mProduct.getId()));
        }

    }

    public String getSumWeight() {
        return mSumBarcodeInfo.getSumWeight();
    }

    public String getSumPlaces() {
        return mSumBarcodeInfo.getSumPlaces();
    }

    public void setInitBarcode(String initBarcode) {
        mProduct.setInitBarcode(initBarcode);
        refresh();
    }

    public CharSequence getTextMessageSeparator() {
        if(mSeporatorIterator.getError()){
            return mSeporatorIterator.getMessError();
        }

        return "Вес: " + Float.toString(mSeporatorIterator.getWeight());

    }

    public void onClickCancel() {
        getViewState().finishView();
    }
}
