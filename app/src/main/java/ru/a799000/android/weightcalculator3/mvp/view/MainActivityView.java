package ru.a799000.android.weightcalculator3.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 30.05.2017.
 */

public interface MainActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void startProductsActivityView();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startSettingsActivityView();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showMessage(String str);


}
