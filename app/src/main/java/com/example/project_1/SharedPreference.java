package com.example.project_1;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreference {

        public static SharedPreferences getSpManager() {
            return PreferenceManager.getDefaultSharedPreferences(App.instance);
        }

        public static void saveString(String key, String value) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, value);
            editor.apply();
        }

        public static String getSavedString(String key) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            return sp.getString(key, "");
        }

        public static void saveInt(String key, int value) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        public static int getSavedInt(String key) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            return sp.getInt(key, 0);
        }

        public static void saveBoolean(String key, Boolean value) {

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

        public static boolean getSavedBoolean(String key) {

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            return sp.getBoolean(key, false);

        }

        public static void saveLong(String key, long value) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong(key, value);
            editor.apply();
        }

        public static long getSavedLong(String key) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(App.instance);
            return sp.getLong(key, 0);
        }
}
