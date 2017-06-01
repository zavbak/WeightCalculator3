package ru.a799000.android.weightcalculator3.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.a799000.android.weightcalculator3.repository.settings.RepoSettingsI;
import ru.a799000.android.weightcalculator3.repository.settings.RepoSettingsMenager;


@Module
public final class RepoModule {

    Context mContext;

    public RepoModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    RepoSettingsI provideRepoSettingsI(){
        return RepoSettingsMenager.getInstance(mContext);
    }

}