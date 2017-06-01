package ru.a799000.android.weightcalculator3.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 30.05.2017.
 */

public interface SettingsActivityView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void refreshView();

    @StateStrategyType(SkipStrategy.class)
    void finishView();
}
