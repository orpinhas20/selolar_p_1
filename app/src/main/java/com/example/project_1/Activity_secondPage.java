package com.example.project_1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_secondPage extends AppCompatActivity {

    public static final String WINNER = "winner";

    private TextView winnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        findView();
        String winner = getIntent().getStringExtra(String.valueOf(WINNER));
        winnerName.setText("" + winner);
    }
    private void findView(){
        winnerName = findViewById(R.id.secondPage_TV_winner);
    }
}