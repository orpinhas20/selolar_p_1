package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_main extends AppCompatActivity {

    private GameManager gameManager;
    private ImageView main_IMG_card1;
    private ImageView main_IMG_card2;
    private ImageView winnerButton;
    private TextView player1Score;
    private TextView player2Score;
    private TextView main_TV_player;
    private TextView main_TV_computer;
    private TextView main_TV_frase;
    private ProgressBar pb;
    private int index;
    private int p1Score;
    private int p2Score;
    private GameManager.Player winner;
    private TableFragment fragment_table;
    private CountDownTimer mCountDownTimer;
    private boolean isGameEnded = false;
    private String playerName;
    private Timer myTimer;

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
//      Glide.with(this).load(R.drawable.start_card).into(main_IMG_card1);
//      Glide.with(this).load(R.drawable.start_card).into(main_IMG_card2);
        setGamePlane();
        initEvents();
        fragment_table = new TableFragment();
        //fragment_table.setCallBack_table(callBack_table);
        myTimer = new Timer();
        getPlayerName();

    }

    private void startTimerTask() {
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (index < gameManager.MAX_CARDS-1) {
                    randomizeNewCards();
                    if (myTimer!=null) {
                        startTimerTask();
                        pb.setProgress(index);
                    }
                } else {
                    myTimer.cancel();
                    myTimer = null;
                     // Display results:
                    displayTheWinner(winner);
                }
            }
        }, 300);
    }

    private void randomizeNewCards() {
        // Make noise for click:
        MediaPlayer mp = MediaPlayer.create(Activity_main.this, R.raw.button_click);
        mp.start();
        // If there are still cards to play with:
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setWinner();
                    index++;
                    setGamePlane();
                }
            });




    }

    private void getPlayerName() {
        playerName = SharedPreference.getSavedString(Const.PLAYER_NAME_KEY);
        main_TV_player.setText(playerName);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    private void findViews() {
        main_IMG_card1 = findViewById(R.id.main_IMG_card1);
        main_IMG_card2 = findViewById(R.id.main_IMG_card2);
        main_TV_player = findViewById(R.id.main_TV_player);
        main_TV_computer = findViewById(R.id.main_TV_computer);
        winnerButton = findViewById(R.id.main_layout_Button);
        player1Score = findViewById(R.id.main_layout_player1_score);
        player2Score = findViewById(R.id.main_layout_player2_score);
        pb = findViewById(R.id.main_PB);
        main_TV_frase = findViewById(R.id.main_TV_frase);

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
    private void setWinner() {
        // Get the current cards for the players:
        Card p1Card = this.gameManager.getP1Card(this.index);
        Card p2Card = this.gameManager.getP2Card(this.index);
        this.winner = this.gameManager.checkWinner(p1Card, p2Card);

        // Add score to the current winner:
        switch (this.winner) {
            case Player1:
                ++this.p1Score;
                Log.d("CardWar", "The winner is " + playerName + " (spiderman), total score of: " + this.p1Score);
                break;
            case Player2:
                ++this.p2Score;
                Log.d("CardWar", "The winner is computer (batman), total score of: " + this.p2Score);
                break;
            case Default:
                break;
        }

        // Display players score:
        player1Score.setText("" + this.p1Score);
        player2Score.setText("" + this.p2Score);
    }

    /* Setup events for the layout components. */
    private void initEvents() {

        winnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimerTask();
                winnerButton.setVisibility(View.INVISIBLE);
                main_TV_frase.setVisibility(v.VISIBLE);
            }
        });

    }

    /* Active the second activity. */
    private void displayTheWinner(GameManager.Player win) {
        int winnerScore = 0;
        Intent myIntent = new Intent(Activity_main.this, Activity_secondPage.class);
        // Check who is the winner:
        GameManager.Player winnerInTheGame;
        if (p1Score > p2Score) {
            winnerInTheGame = GameManager.Player.Player1;
            winnerScore = p1Score;
        }

        else if (p1Score < p2Score) {
            winnerInTheGame = GameManager.Player.Player2;
            winnerScore = p2Score;
        }
        else
            winnerInTheGame = GameManager.Player.Default;

        // Active the results page:
        myIntent.putExtra(Const.PLAYER_WINNER_KEY, winnerInTheGame.getValue());
        myIntent.putExtra(Const.PLAYER_WINNER_KEY, winnerInTheGame.getValue());
        myIntent.putExtra(Const.PLAYER_SCORE_KEY, winnerScore);
        startActivity(myIntent);
        finish();
    }
}