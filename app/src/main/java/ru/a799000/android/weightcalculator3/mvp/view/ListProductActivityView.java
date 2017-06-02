package ru.a799000.android.weightcalculator3.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;

/**
 * Created by Alex on 30.05.2017.
 */

public interface ListProductActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void startDetailProductActivityView(String idProdact);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void refresh(RealmResults<Product> list);
}
