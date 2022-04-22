package com.androidavanzado.minitwitter.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidavanzado.minitwitter.R;

public class SharedPreferenceManager {

    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";

    public SharedPreferenceManager() { }

    private static SharedPreferences getSharedPreference() {
        return MyApp.getContext()
                .getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public static void setStringValue(String dataLabel, String dataValue) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(dataLabel, dataValue);
        editor.commit();
    }

    public static void setBooleanValue(String dataLabel, Boolean dataValue) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(dataLabel, dataValue);
        editor.commit();
    }

    public static String getStringValue(String dataLabel) {
        return getSharedPreference().getString(dataLabel, null);
    }

    public static Boolean getBooleanValue(String dataLabel) {
        return getSharedPreference().getBoolean(dataLabel, false);
    }
}
