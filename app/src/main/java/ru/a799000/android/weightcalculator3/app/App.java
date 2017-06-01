package ru.a799000.android.weightcalculator3.app;

import android.app.Application;

import io.realm.Realm;
import ru.a799000.android.weightcalculator3.di.ApplicationComponent;
import ru.a799000.android.weightcalculator3.di.ApplicationModule;
import ru.a799000.android.weightcalculator3.di.DaggerApplicationComponent;
import ru.a799000.android.weightcalculator3.di.RepoModule;


public class App extends Application {

    private static ApplicationComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /*
    Инициализация всего
     */
    void init(){

        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .repoModule(new RepoModule(this))
                .build();

        Realm.init(this);
        Realm.setDefaultConfiguration(mAppComponent.getRealmConfiguration());
    }

    public static ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
