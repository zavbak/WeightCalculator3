package ru.a799000.android.weightcalculator3.mvp.view;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.realm.RealmList;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Barcode;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;

/**
 * Created by Alex on 23.05.2017.
 */

public interface ListBarcodeActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void startDetailBarcodeActivityView(String idProduct, String idBarcode, String barcode);

    @StateStrategyType(SkipStrategy.class)
    void registerBarcodeReceiver();
    @StateStrategyType(SkipStrategy.class)
    void unregisterReceiver();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void refresh(RealmList<Barcode> list);
}
