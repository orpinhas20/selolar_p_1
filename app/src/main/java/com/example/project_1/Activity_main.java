package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

public class Activity_main extends AppCompatActivity {

    private ImageView main_IMG_card1;
    private ImageView main_IMG_card2;

    private int winner = 0;
    String winnerName = "";
    private int p1Count = 0;
    private int p2Count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_card_game);
        findViews();
        GameManager.initCardGame();
//        gamePlane();
//        openSecondPage( winnerName );
    }

    private void findViews(){
        main_IMG_card1 = findViewById(R.id.main_IMG_card1);
        main_IMG_card2 = findViewById(R.id.main_IMG_card2);
    }

    private void gamePlane(){
        for(int i = 0;i<26;i++) {
            main_IMG_card1.setImageResource(Integer.parseInt(GameManager.getP1Array().get(i).getName()));
            main_IMG_card2.setImageResource(Integer.parseInt(GameManager.getP2Array().get(i).getName()));
            winner = GameManager.checkWinnner(GameManager.getP1Array().get(i),GameManager.getP2Array().get(i));
            if(winner == 1)
                p1Count++;
            else
                p2Count++;
        }
        if( p1Count >= p2Count )
            winnerName = "P1 you are the winner" ; //player a wins
        else
            winnerName = "P2 you are the winner" ; //player b wins
    }
    private void openSecondPage(String win){
       Intent myIntent = new Intent(Activity_main.this,Activity_secondPage.class);
        myIntent.putExtra(Activity_secondPage.WINNER,winnerName);
        startActivity(myIntent);
    }

}