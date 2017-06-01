package ru.a799000.android.weightcalculator3.repository.settings;



public interface RepoSettingsI {
    Settings getSettings();
    void saveSettings(Settings settings);
}
