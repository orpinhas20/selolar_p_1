package com.example.project_1;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

        private static SharedPreference instance;
        private SharedPreferences prefs;

        private SharedPreference(Context context) {
            prefs = context.getSharedPreferences("spFile", Context.MODE_PRIVATE);
        }

        public static void init(Context context) {
            if (instance == null) {
                instance = new SharedPreference(context.getApplicationContext());
            }
        }

        public static SharedPreference getInstance() {
            return instance;
        }

        public void putString(String key, String value) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.apply();
        }

        public String getString(String key, String def) {
            return prefs.getString(key, def);
        }

        public void removeKey(String key) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.apply();

    }
}
