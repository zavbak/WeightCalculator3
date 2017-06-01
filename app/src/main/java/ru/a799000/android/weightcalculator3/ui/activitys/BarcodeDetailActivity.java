package ru.a799000.android.weightcalculator3.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.presenters.BarcodeDetailActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.view.BarcodeDetailActivityView;


/**
 * Created by Alex on 23.05.2017.
 */

public class BarcodeDetailActivity extends MvpAppCompatActivity implements BarcodeDetailActivityView {

    static String ID = "id";
    static String ID_BARCODE = "id_barcode";
    static String BARCODE = "barcode";

    @BindView(R.id.tilId)
    TextInputLayout tilId;

    @BindView(R.id.tilBarcode)
    TextInputLayout tilBarcode;

    @BindView(R.id.tilWeight)
    TextInputLayout tilWeight;

    @BindView(R.id.tilPlaces)
    TextInputLayout tilPlaces;

    @InjectPresenter
    BarcodeDetailActivityPresenter mPresenter;

    public static Intent getIntent(final Context context, String idProduct, String idBarcode,String barcode) {
        Intent intent = new Intent(context, BarcodeDetailActivity.class);
        intent.putExtra(ID,idProduct);
        intent.putExtra(ID_BARCODE,idBarcode);
        intent.putExtra(BARCODE,barcode);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_barcode_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String idProduct = intent.getStringExtra(ID);
        String idBarcode = intent.getStringExtra(ID_BARCODE);
        String barcode = intent.getStringExtra(BARCODE);
        mPresenter.setProduct(idProduct);
        mPresenter.setBarcode(idBarcode,barcode);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.refresh();
    }

    @Override
    public void refreshView() {
        tilId.getEditText().setText(mPresenter.getId());
        tilBarcode.getEditText().setText(mPresenter.getBarcode());
        tilPlaces.getEditText().setText(mPresenter.getPlaces());
        tilWeight.getEditText().setText(mPresenter.getWeight());
    }

    @Override
    public void finishView() {
        finish();
    }


    @OnClick(R.id.btOk)
    public void onClickOk() {

        mPresenter.save(
                tilBarcode.getEditText().getText().toString(),
                tilWeight.getEditText().getText().toString(),
                tilPlaces.getEditText().getText().toString());


    }

    @OnClick(R.id.btCancel)
    public void onClickDell() {
        mPresenter.btCancel();
    }




}
