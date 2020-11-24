package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_secondPage extends AppCompatActivity {

    public static final String WINNER = "WINNER";
    private TextView promptWinner;
    private ImageView winnerAvatar;
    private GameManager.Player winner;

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
    }

    /* Get data from main activity and display the winner. */
    private void getWinnerFromIntent() {
        Intent intent = getIntent();

        // Cast intent data to Player type:
        int valueFromIntent = intent.getIntExtra(WINNER, 0);
        GameManager.Player winner = GameManager.Player.values()[valueFromIntent];

        // Set the player name and the player avatar:
        if(winner != GameManager.Player.Default){
            String winnerName = (winner == GameManager.Player.Player1 ? "Player 1" : "Player 2");
            String winnerAvatarName = (winner == GameManager.Player.Player1 ? "spiderman" : "batman");

            // Display the results to the user:
            promptWinner.setText("The winner is: " + winnerName);
            winnerAvatar.setImageResource(Utils.getImageId(this, winnerAvatarName));
        }
        else {
            promptWinner.setText("There was a draw !");
        }
    }
}