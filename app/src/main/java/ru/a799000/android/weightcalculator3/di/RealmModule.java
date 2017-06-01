package ru.a799000.android.weightcalculator3.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;
import ru.a799000.android.weightcalculator3.repository.realm.init.RealmMigration;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IBarcodeRepository;
import ru.a799000.android.weightcalculator3.repository.realm.repository.IProductRepository;
import ru.a799000.android.weightcalculator3.repository.realm.repository.impl.BarcodeRepository;
import ru.a799000.android.weightcalculator3.repository.realm.repository.impl.ProductRepository;


@Module
public class RealmModule {

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration(){
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new RealmMigration())
                .build();
    }

    @Provides
    @Singleton
    IProductRepository provideProductRepository(){
        return  new ProductRepository();
    }

    @Provides
    @Singleton
    IBarcodeRepository provideIBarcodeRepository(){
        return new BarcodeRepository();
    }






}
