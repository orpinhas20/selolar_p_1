package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_lastPage extends AppCompatActivity {

    private MapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.mapFragment = new MapFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.TOP_FRAGMENT_MAP, mapFragment)
                .commit();
    }
}
