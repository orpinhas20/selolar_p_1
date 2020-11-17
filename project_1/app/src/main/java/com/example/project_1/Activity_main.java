package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class Activity_main extends AppCompatActivity {

    private int winner = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_card_game);
    }


    private void initViews(){

    }
//build a methode that checks the winner
//put '0' for player a win
//put '1' for player b win
    private void openSecondPage(){
       Intent myIntent = new Intent(Activity_main.this,Activity_secondPage.class);
        myIntent.putExtra(Activity_secondPage.EXTRA_KEY_WINNER,winner);
    }

}