package ru.a799000.android.weightcalculator3.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.presenters.ProdactDetailPresenter;
import ru.a799000.android.weightcalculator3.mvp.view.ProdactDetailView;
import ru.a799000.android.weightcalculator3.repository.barcode.BarcodeDataBroadcastReceiver;


/**
 * Created by Alex on 23.05.2017.
 */

public class ProdactDetailActivity extends MvpAppCompatActivity implements ProdactDetailView {

    static String ID = "id";

    @InjectPresenter
    ProdactDetailPresenter mPresenter;

    BarcodeDataBroadcastReceiver mBarcodeDataBroadcastReceiver;


    @BindView(R.id.tilId)
    TextInputLayout tilId;

    @BindView(R.id.tilBarcode)
    TextInputLayout tilBarcode;

    @BindView(R.id.tilName)
    TextInputLayout tilName;

    @BindView(R.id.tilStart)
    TextInputLayout tilStart;

    @BindView(R.id.tilFinish)
    TextInputLayout tilFinish;

    @BindView(R.id.tilCof)
    TextInputLayout tilCof;

    @BindView(R.id.tvSumWeight)
    TextView tvSumWeight;

    @BindView(R.id.tvSumPlaces)
    TextView tvSumPlaces;

    @BindView(R.id.tvMessageBarcodeSeparator)
    TextView tvMessageBarcodeSeparator;

    public static Intent getIntent(final Context context, String id) {
        Intent intent = new Intent(context, ProdactDetailActivity.class);
        intent.putExtra(ID,id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_prodact_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra(ID);

        mBarcodeDataBroadcastReceiver = new BarcodeDataBroadcastReceiver(barcode -> {mPresenter.setInitBarcode(barcode);});

        mPresenter.setProduct(id);
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
    public void refreshView() {
        tilId.getEditText().setText(mPresenter.getId());
        tilBarcode.getEditText().setText(mPresenter.getBarcode());
        tilName.getEditText().setText(mPresenter.getName());
        tilStart.getEditText().setText(mPresenter.getStart());
        tilFinish.getEditText().setText(mPresenter.getFinish());
        tilCof.getEditText().setText(mPresenter.getCof());
        tvSumPlaces.setText(mPresenter.getSumWeight());
        tvSumWeight.setText(mPresenter.getSumPlaces());
        tvMessageBarcodeSeparator.setText(mPresenter.getTextMessageSeparator());
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void startBarcodesActivityView(String id) {
        startActivity(BarcodesActivity.getIntent(this,id));
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


    //********************************************************************************************
    // EVENTS

    @OnClick(R.id.btOk)
    public void onClickOk(){
        mPresenter.save( tilBarcode.getEditText().getText().toString(),
                         tilName.getEditText().getText().toString(),
                         tilStart.getEditText().getText().toString(),
                         tilFinish.getEditText().getText().toString(),
                         tilCof.getEditText().getText().toString());
    }


    @OnClick(R.id.btCancel)
    public void onClickCancel(){
        mPresenter.onClickCancel();
    }

    @OnClick(R.id.btBarcodes)
    public void onClickBarcodes(){
        mPresenter.onClickBarcodes();
    }




}
