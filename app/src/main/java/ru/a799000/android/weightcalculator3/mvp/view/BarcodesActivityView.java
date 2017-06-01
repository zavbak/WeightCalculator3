package ru.a799000.android.weightcalculator3.mvp.view;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 23.05.2017.
 */

public interface BarcodesActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void startDetailBarcodeActivityView(String idProduct, String idBarcode, String barcode);

    @StateStrategyType(SkipStrategy.class)
    void registerBarcodeReceiver();
    @StateStrategyType(SkipStrategy.class)
    void unregisterReceiver();
}
