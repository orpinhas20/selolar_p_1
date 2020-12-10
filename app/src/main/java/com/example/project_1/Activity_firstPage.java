package com.example.project_1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_firstPage extends AppCompatActivity {

    private TextView first_TXT_welcome;
    private EditText first_TXT_enterName;
    private ImageView first_IMV_game;
    private Button first_BTN_TOPTEN;
    private Button first_BTN_Start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViews();


        first_BTN_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayerName();
            }
        });

    }

    private void findViews() {
        first_TXT_welcome = findViewById(R.id.first_TXT_welcome);
        first_TXT_enterName = findViewById(R.id.first_TXT_enterName);
        first_IMV_game = findViewById(R.id.first_IMV_game);
        first_BTN_TOPTEN = findViewById(R.id.first_BTN_TOPTEN);
        first_BTN_Start = findViewById(R.id.first_BTN_Start);
    }

    private void getPlayerName(){
        String playerName = first_TXT_enterName.getText().toString();

        if (playerName.isEmpty()){
            Toast.makeText(this, "יש להזין שם", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent myIntent = new Intent(Activity_firstPage.this, Activity_main.class);
        // Active the game page:
        myIntent.putExtra(Const.PLAYER_NAME_KEY ,playerName);
        startActivity(myIntent);
    }



}
