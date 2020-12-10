package com.example.project_1;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreference.init(this);
        Signal.init(this);

    }

}