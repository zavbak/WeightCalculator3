package ru.a799000.android.weightcalculator3.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.presenters.BarcodesActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.view.BarcodesActivityView;
import ru.a799000.android.weightcalculator3.repository.barcode.BarcodeDataBroadcastReceiver;
import ru.a799000.android.weightcalculator3.ui.adapters.AdapterListBarcodes;


/**
 * Created by Alex on 23.05.2017.
 */

public class BarcodesActivity extends MvpAppCompatActivity implements BarcodesActivityView {

    static String ID = "id";

    @InjectPresenter
    BarcodesActivityPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    BarcodeDataBroadcastReceiver mBarcodeDataBroadcastReceiver;

    public static Intent getIntent(final Context context, String id) {
        Intent intent = new Intent(context, BarcodesActivity.class);
        intent.putExtra(ID,id);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcodes_activity);
        ButterKnife.bind(this);

        mBarcodeDataBroadcastReceiver = new BarcodeDataBroadcastReceiver(barcode -> {mPresenter.onBarcode(barcode);});

        Intent intent = getIntent();
        String id = intent.getStringExtra(ID);
        mPresenter.setProduct(id);
        init();
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void registerBarcodeReceiver() {

        IntentFilter intentFilter = new IntentFilter("DATA_SCAN");
        registerReceiver(mBarcodeDataBroadcastReceiver, intentFilter);
    }

    @Override
    public void unregisterReceiver() {
        unregisterReceiver(mBarcodeDataBroadcastReceiver);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        mRecyclerView.setAdapter(new AdapterListBarcodes(mPresenter.getBarcodes(), id -> mPresenter.clickItem(id)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void startDetailBarcodeActivityView(String idProduct,String idBarcode,String barcode) {
        startActivity(BarcodeDetailActivity.getIntent(this,idProduct,idBarcode,barcode));
    }



}
