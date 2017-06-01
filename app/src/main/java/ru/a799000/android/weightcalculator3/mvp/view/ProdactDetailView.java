package ru.a799000.android.weightcalculator3.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 23.05.2017.
 */

public interface ProdactDetailView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void refreshView();

    @StateStrategyType(SkipStrategy.class)
    void finishView();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startBarcodesActivityView(String id);

    @StateStrategyType(SkipStrategy.class)
    void registerBarcodeReceiver();
    @StateStrategyType(SkipStrategy.class)
    void unregisterReceiver();
}
