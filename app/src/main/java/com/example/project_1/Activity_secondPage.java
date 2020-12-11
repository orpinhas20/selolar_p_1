package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_secondPage extends AppCompatActivity {

    private TextView promptWinner;
    private ImageView winnerAvatar;
    private GameManager.Player winner;
    private String playerName;
    private Timer myTimer;

    public Activity_secondPage() {
        promptWinner = null;
        winnerAvatar = null;
        winner = GameManager.Player.Default;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        findViews();
        getWinnerFromIntent();
        MediaPlayer applause = MediaPlayer.create(Activity_secondPage.this,R.raw.applause);
        applause.start();
        applause.setVolume(1.0f,1.0f);
    }

    /* Setup class members from layout components. */
    private void findViews(){
        this.promptWinner = findViewById(R.id.promptWinner);
        this.winnerAvatar = findViewById(R.id.winnerAvatar);

        // Clear the avatar:
        if(this.winnerAvatar != null){
            this.winnerAvatar.setImageResource(0);
        }
    }

    private void goToNextScreen() {
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent myIntent = new Intent(Activity_secondPage.this, Activity_lastPage.class);
                startActivity(myIntent);
                finish();
            }
        }, 3000);

    }

    /* Get data from main activity and display the winner. */
    private void getWinnerFromIntent() {
        String winnerName = "";
        Intent intent = getIntent();
        // Cast intent data to Player type:
        int valueFromIntent = intent.getIntExtra(Const.PLAYER_WINNER_KEY, 0);
        playerName = SharedPreference.getSavedString(Const.PLAYER_NAME_KEY);
        GameManager.Player winner = GameManager.Player.values()[valueFromIntent];
        int winnerScore = intent.getIntExtra(Const.PLAYER_SCORE_KEY, 0);
        // Set the player name and the player avatar:
        if(winner != GameManager.Player.Default){
            winnerName = (winner == GameManager.Player.Player1 ? playerName : "computer");
            String winnerAvatarName = (winner == GameManager.Player.Player1 ? "spiderman" : "batman");


            if (!winnerName.equals("computer")){
                Record record = new Record(winnerName,winnerScore);
                App.instance.saveRecordToSP(record);
            }

            // Display the results to the user:
            promptWinner.setText("The winner is: " + winnerName);
            winnerAvatar.setImageResource(Utils.getImageId(this, winnerAvatarName));
        }
        else {
            promptWinner.setText("There was a draw !");
        }
        goToNextScreen();

    }
}