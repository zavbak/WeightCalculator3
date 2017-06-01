package ru.a799000.android.weightcalculator3.ui.activitys;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.a799000.android.weightcalculator3.R;
import ru.a799000.android.weightcalculator3.mvp.presenters.MainActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.presenters.SettingsActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.view.MainActivityView;
import ru.a799000.android.weightcalculator3.mvp.view.SettingsActivityView;
import ru.a799000.android.weightcalculator3.repository.settings.Settings;

public class SettingsActivity extends MvpAppCompatActivity implements SettingsActivityView {

    @BindView(R.id.tilFileName)
    TextInputLayout tilFileName;

    @InjectPresenter
    SettingsActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @OnClick(R.id.btCancel)
    public void OnClickCancel(){
        mPresenter.onClickCancel();
    }

    @OnClick(R.id.btOk)
    public void OnClickOk(){
        Settings settings = new Settings();
        settings.mName = tilFileName.getEditText().getText().toString();
        mPresenter.onClickOK(settings);
    }


    @Override
    public void refreshView() {
        tilFileName.getEditText().setText("" + mPresenter.getSettings().mName);
    }

    @Override
    public void finishView() {
        finish();
    }


}
