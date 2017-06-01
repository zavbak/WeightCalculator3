package ru.a799000.android.weightcalculator3.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.RealmConfiguration;
import ru.a799000.android.weightcalculator3.mvp.model.interactor.BarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactor.ProductInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactor.SumBarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.presenters.BarcodeDetailActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.presenters.BarcodesActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.presenters.ListProductActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.presenters.MainActivityPresenter;
import ru.a799000.android.weightcalculator3.mvp.presenters.ProdactDetailPresenter;
import ru.a799000.android.weightcalculator3.mvp.presenters.SettingsActivityPresenter;
import ru.a799000.android.weightcalculator3.repository.tovarfile.SendProductFile;


/**
 * Created by user on 17.05.2017.
 */

@Singleton
@Component(modules={ApplicationModule.class,RepoModule.class,RealmModule.class})
public interface ApplicationComponent {
    Context getContext();
    RealmConfiguration getRealmConfiguration();

    void injectSettingsActivityPresenter(SettingsActivityPresenter settingsActivityPresenter);
    void injectMainActivityPresenter(MainActivityPresenter mainActivityPresenter);

    void injectBarcodeInteractor(BarcodeInteractor barcodeInteractor);

    void injectProductInteractor(ProductInteractor productInteractor);

    void injectListProductActivityPresenter(ListProductActivityPresenter listProductActivityPresenter);

    void injectSumBarcodeInteractor(SumBarcodeInteractor sumBarcodeInteractor);

    void injectProdactDetailPresenter(ProdactDetailPresenter prodactDetailPresenter);

    void injectBarcodesActivityPresenter(BarcodesActivityPresenter barcodesActivityPresenter);

    void injectBarcodeDetailActivityPresenter(BarcodeDetailActivityPresenter barcodeDetailActivityPresenter);

    void injectSendProductFile(SendProductFile sendProductFile);
}
