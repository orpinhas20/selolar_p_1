package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_main extends AppCompatActivity {

    private ImageView main_IMG_card1;
    private ImageView main_IMG_card2;
    private ImageView winnerButton;
    private TextView player1Score;
    private TextView player2Score;
    private int index = 0;
    private int p1Score = 0;
    private int p2Score = 0;
    private int winner = 0;
    int win = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_card_game);
        findViews();
        GameManager.initCardGame();
        setGamePlane();
        initEvents();
    }

    /* Setup class members from layout components. */
    private void findViews() {
        main_IMG_card1 = findViewById(R.id.main_IMG_card1);
        main_IMG_card2 = findViewById(R.id.main_IMG_card2);
        winnerButton = findViewById(R.id.main_layout_Button);
        player1Score = findViewById(R.id.main_layout_player1_score);
        player2Score = findViewById(R.id.main_layout_player2_score);
    }

    /* Update the game layout. */
    private void setGamePlane() {
        // Set the card for player 1:
        String p1CardImageName = GameManager.getP1Array().get(this.index).getName();
        int p1CardResourceId = Utils.getImageId(this, p1CardImageName);
        main_IMG_card1.setImageResource(p1CardResourceId);

        // Set the card for player 2:
        String p2CardImageName = GameManager.getP2Array().get(this.index).getName();
        int p2CardResourceId = Utils.getImageId(this, p2CardImageName);
        main_IMG_card2.setImageResource(p2CardResourceId);

        // Set players score:
        player1Score.setText("" + this.p1Score);
        player2Score.setText("" + this.p2Score);
    }

    /* Update the players score. */
    private void setWinner(){
        // Check which player is the winner in this round:
        this.winner = GameManager.checkWinner(GameManager.getP1Array().get(index),GameManager.getP2Array().get(index));
        if(this.winner == 1)
            this.p1Score++;
        else
            this.p2Score++;
    }

    /* Setup events for the layout components. */
    private void initEvents(){
        this.winnerButton.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {

                if (arg1.getAction() == MotionEvent.ACTION_DOWN){
                    MediaPlayer button_click = MediaPlayer.create(Activity_main.this,R.raw.button_click);
                    button_click.start();
                    button_click.setVolume(1.0f,1.0f);
                    // Increase the card index and check if there are no more cards:
                    if(++index < GameManager.MAX_CARDS){
                        // Keep playing:
                        setWinner();
                        setGamePlane();
                    } else{
                        openSecondPage(winner);
                    }
                }
                return true;
            }
        });
    }

    /* Active the second activity. */
    private void openSecondPage(int win){
        Intent myIntent = new Intent(Activity_main.this, Activity_secondPage.class);
        myIntent.putExtra(Activity_secondPage.WINNER, win);
        startActivity(myIntent);
    }

}