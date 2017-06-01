package ru.a799000.android.weightcalculator3.repository.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;



public class RepoSettingsMenager implements RepoSettingsI {

    private  static final String MY_APP_PREFERENCES = "ca7eed88-2409-4de7-b529-52598af76734";
    private static final String PREF_SETTINGS = "963dfbb5-5f25-4fa9-9a9e-6766bfebfda8";



    private SharedPreferences sharedPrefs;
    private static RepoSettingsMenager instance;

    private RepoSettingsMenager(Context context){
        //using application context just to make sure we don't leak any activities
        sharedPrefs = context.getApplicationContext().getSharedPreferences(MY_APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static synchronized RepoSettingsMenager getInstance(Context context){
        if(instance == null)
            instance = new RepoSettingsMenager(context);
        return instance;
    }

    @Override
    public Settings getSettings() {
        String sSetting = sharedPrefs.getString(PREF_SETTINGS, null);
        if(sSetting == null){
            return new Settings();
        }else{

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Settings settings = gson.fromJson(sSetting, Settings.class);
            return  settings;

        }

    }

    @Override
    public void saveSettings(Settings settings) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PREF_SETTINGS, gson.toJson(settings));
        editor.apply();

    }
}
