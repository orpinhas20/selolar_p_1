package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_main extends AppCompatActivity {

    private GameManager gameManager;
    private ImageView main_IMG_card1;
    private ImageView main_IMG_card2;
    private ImageView winnerButton;
    private TextView player1Score;
    private TextView player2Score;
    private int index;
    private int p1Score;
    private int p2Score;
    private GameManager.Player winner;
    private Fragment_Table fragment_table;
    private Fragment_Map fragment_map;

    public Activity_main() {
        this.main_IMG_card1 = null;
        this.main_IMG_card2 = null;
        this.player1Score = null;
        this.player2Score = null;
        this.index = 0;
        this.p1Score = 0;
        this.p2Score = 0;
        this.winner = GameManager.Player.Default;
        this.gameManager = GameManager.getInstance();
        this.gameManager.initCardGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_card_game);
        findViews();
        setGamePlane();
        initEvents();
        fragment_table = new Fragment_Table();
        fragment_table.setCallBack_top(callBack_top);

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
        // Set card for player 1:
        Card p1Card = this.gameManager.getP1Card(this.index);
        int p1CardResourceId = Utils.getImageId(this, p1Card.getName());
        main_IMG_card1.setImageResource(p1CardResourceId);

        // Set card for player 2:
        Card p2Card = this.gameManager.getP2Card(this.index);
        int p2CardResourceId = Utils.getImageId(this, p2Card.getName());
        main_IMG_card2.setImageResource(p2CardResourceId);
    }

    /* Update the players score. */
    private void setWinner(){
        // Get the current cards for the players:
        Card p1Card = this.gameManager.getP1Card(this.index);
        Card p2Card = this.gameManager.getP2Card(this.index);
        this.winner = this.gameManager.checkWinner(p1Card, p2Card);

        // Add score to the current winner:
        switch(this.winner){
            case Player1:
                ++this.p1Score;
                Log.d("CardWar", "The winner is Player 1 (spiderman), total score of: " + this.p1Score);
                break;
            case Player2:
                ++this.p2Score;
                Log.d("CardWar", "The winner is Player 2 (batman), total score of: " + this.p2Score);
                break;
            case Default:
                break;
        }

        // Display players score:
        player1Score.setText("" + this.p1Score);
        player2Score.setText("" + this.p2Score);
    }

    /* Setup events for the layout components. */
    private void initEvents(){
        this.winnerButton.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN){
                    // Make noise for click:
                    MediaPlayer buttonClick = MediaPlayer.create(Activity_main.this, R.raw.button_click);
                    buttonClick.start();

                    // If there are still cards to play with:
                    if(index < gameManager.MAX_CARDS){
                        // Run on UI thread:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setWinner();
                                index++;
                                setGamePlane();
                            }
                        });
                    } else{
                        // Display results:
                        displayTheWinner(winner);
                    }
                }
                return true;
            }
        });
    }

    /* Active the second activity. */
    private void displayTheWinner(GameManager.Player win){
        Intent myIntent = new Intent(Activity_main.this, Activity_secondPage.class);

        // Check who is the winner:
        GameManager.Player winnerInTheGame;
        if(p1Score > p2Score)
            winnerInTheGame = GameManager.Player.Player1;
        else if(p1Score < p2Score)
            winnerInTheGame = GameManager.Player.Player2;
        else
            winnerInTheGame = GameManager.Player.Default;

        // Active the results page:
        myIntent.putExtra(Activity_secondPage.WINNER, winnerInTheGame.getValue());
        startActivity(myIntent);
    }

    private CallBack_Top callBack_top = new CallBack_Top() {
        @Override
        public void changeTitle(String str) {
           // main_BTN_updateTime.setText(str);
        }

        @Override
        public void displayLocation(double lat, double lon) {
            fragment_map.showLocationOnMap(lat, lon);
        }
    };
}