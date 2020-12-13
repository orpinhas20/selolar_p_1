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

public class Activity_firstPage extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSIONS_REQUEST_LOCATION = 99;

    private TextView first_TXT_welcome;
    private EditText first_TXT_enterName;
    private ImageView first_IMV_game;
    private Button first_BTN_TOPTEN;
    private Button first_BTN_Start;
    String playerName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViews();

        // Set location services permissions:
        this.getLocationPermission();



    }

    private void findViews() {
        first_TXT_welcome = findViewById(R.id.first_TXT_welcome);
        first_TXT_enterName = findViewById(R.id.first_TXT_enterName);
        first_IMV_game = findViewById(R.id.first_IMV_game);
        first_BTN_TOPTEN = findViewById(R.id.first_BTN_TOPTEN);
        first_BTN_Start = findViewById(R.id.first_BTN_Start);
    }

    private void getPlayerName() {
        playerName = first_TXT_enterName.getText().toString();
        if (playerName.isEmpty()) {
            Toast.makeText(this, "enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent myIntent = new Intent(Activity_firstPage.this, Activity_main.class);
        startActivity(myIntent);
        // Active the game page:
        SharedPreference.saveString(Const.PLAYER_NAME_KEY,playerName);
        startActivity(myIntent);
    }

    private void getLocationPermission(){
        if(Utils.checkLocationPermission(
                getApplication().getBaseContext(),
                this,
                PERMISSIONS_REQUEST_LOCATION)){
            App.instance.setLocationStatus(true);
        }else{
            App.instance.setLocationStatus(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_BTN_Start :
                getPlayerName();
                break;
            case R.id.first_BTN_TOPTEN :
                Intent activity2Intent = new Intent(Activity_firstPage.this, Activity_lastPage.class);
                startActivity(activity2Intent);
                break;
        }

    }
}
