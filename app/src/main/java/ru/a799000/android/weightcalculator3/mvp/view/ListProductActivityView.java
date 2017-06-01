package ru.a799000.android.weightcalculator3.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 30.05.2017.
 */

public interface ListProductActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void startDetailProductActivityView(String idProdact);
}
