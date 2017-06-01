package ru.a799000.android.weightcalculator3.ui.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.presenters.MainActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.view.MainActivityView;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter mPresenter;

    @BindView(R.id.tvMassage)
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @Override
    public void startProductsActivityView() {
        Intent intent = new Intent(this, ListProductActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSettingsActivityView() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String str) {
        tvMessage.setText(str);

    }

    @OnClick(R.id.btProdacts)
    void onClickStartProductActivity(){
        mPresenter.onClickStartProductActivity();
    }

    @OnClick(R.id.btLoad)
    void onClickLoad(){
          mPresenter.Load();
    }

    @OnClick(R.id.btSend)
    void onClickSend(){
        mPresenter.Send();
    }

    @OnClick(R.id.btSettings)
    void onClickStartSettingsActivity(){
         mPresenter.StartSettingsActivity();
    }

}
