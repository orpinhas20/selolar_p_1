package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_secondPage extends AppCompatActivity {

    public static final String WINNER = "WINNER";
    private TextView promptWinner;
    private ImageView winnerAvatar;
    private int winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        findViews();
        getWinnerFromIntent();
    }

    /* Setup class members from layout components. */
    private void findViews(){
        this.promptWinner = findViewById(R.id.promptWinner);
        this.winnerAvatar = findViewById(R.id.winnerAvatar);
    }

    /* Get data from main activity and display the winner. */
    private void getWinnerFromIntent() {
        Intent intent = getIntent();
        int winner = intent.getIntExtra(WINNER, -1);

        // Set the player name and the player avatar:
        if(winner != -1){
            promptWinner.setText("The winner is: " + (winner == 1 ? "Player 1" : "Player 2"));
            String avatarName = (winner == 1 ? "spiderman" : "batman");
            winnerAvatar.setImageResource(Utils.getImageId(this, avatarName));
        }
    }
}