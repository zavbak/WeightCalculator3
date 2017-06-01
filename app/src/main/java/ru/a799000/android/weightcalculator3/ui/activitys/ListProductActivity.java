package ru.a799000.android.weightcalculator3.ui.activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.presenters.ListProductActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.view.ListProductActivityView;
import ru.a799000.android.weightcalculator3.ui.adapters.AdapterListProduct;

/**
 * Created by Alex on 30.05.2017.
 */

public class ListProductActivity extends MvpAppCompatActivity implements ListProductActivityView {

    @InjectPresenter
    ListProductActivityPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        mRecyclerView.setAdapter(new AdapterListProduct(mPresenter.getProducts(), id -> mPresenter.clickItem(id)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onStop();
    }

    void init(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void startDetailProductActivityView(String idProdact) {
        startActivity(ProdactDetailActivity.getIntent(this,idProdact));
    }
}
