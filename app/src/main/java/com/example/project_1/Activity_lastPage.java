package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_lastPage extends AppCompatActivity {

    private String playerName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getWinnerFromIntent() {
        Intent intent = getIntent();
        // Cast intent data to Player type:
        playerName = intent.getStringExtra(Const.PLAYER_NAME_KEY);
        Log.d("pttt","the name is:" + playerName);
    }
}
