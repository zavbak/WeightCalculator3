package ru.a799000.android.weightcalculator3.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.BarcodeInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.ProductInteractor;
import ru.a799000.android.weightcalculator3.mvp.model.interactors.SumBarcodeInteractor;


/**
 * Created by Alex on 24.05.2017.
 */
@Module
public class InteractorModel {
    @Provides
    @Singleton
    ProductInteractor provideRealmConfiguration(){
        return new ProductInteractor();
    }

    @Provides
    @Singleton
    BarcodeInteractor provideBarcodeInteractor(){
        return new BarcodeInteractor();
    }

    @Provides
    @Singleton
    SumBarcodeInteractor provideSumBarcodeInteractor(){
        return new SumBarcodeInteractor();
    }

}
