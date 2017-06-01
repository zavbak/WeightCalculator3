package ru.a799000.android.weightcalculator3.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import ru.a799000.android.weightcalculator3.app.App;
import ru.a799000.android.weightcalculator3.mvp.view.SettingsActivityView;
import ru.a799000.android.weightcalculator3.repository.settings.RepoSettingsI;
import ru.a799000.android.weightcalculator3.repository.settings.Settings;


@InjectViewState
public class SettingsActivityPresenter extends MvpPresenter<SettingsActivityView> {

    @Inject
    RepoSettingsI mRepoSettings;

    public SettingsActivityPresenter() {
        App.getAppComponent().injectSettingsActivityPresenter(this);
    }


    void saveSetting(Settings settings){
        mRepoSettings.saveSettings(settings);
    }

    public Settings getSettings(){
        return mRepoSettings.getSettings();
    }

    public void onClickCancel() {
        getViewState().finishView();
    }

    public void onClickOK(Settings settings) {
        saveSetting(settings);
        getViewState().finishView();
    }

    public void onStart() {
        getViewState().refreshView();
    }
}
