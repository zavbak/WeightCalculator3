package ru.a799000.android.weightcalculator3.ui.activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.model.intities.Product;
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

    void init(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onStop();
    }


    @Override
    public void startDetailProductActivityView(String idProdact) {
        startActivity(ProdactDetailActivity.getIntent(this,idProdact));
    }

    @Override
    public void refresh(RealmResults<Product> list) {
        mRecyclerView.setAdapter(new AdapterListProduct(list, id -> mPresenter.clickItem(id)));
    }
}
