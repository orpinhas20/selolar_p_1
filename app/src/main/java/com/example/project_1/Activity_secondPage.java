package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_secondPage extends AppCompatActivity {

    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";
    private TextView winnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        int winner = getIntent().getIntExtra(EXTRA_KEY_WINNER, -1);
        winnerName.setText("" + winner);

    }
}