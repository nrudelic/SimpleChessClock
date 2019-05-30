package com.rudelic.simplechessclock;
import com.rudelic.simplechessclock.MainMenu.*;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private long firstPlayerTimeLeft;
    private long secondPlayerTimeLeft;

    private boolean firstOnTurn;

    private Button firstPlayerDone;
    private Button secondPlayerDone;

    private CountDownTimer firstCount;
    private CountDownTimer secondCount;

    private boolean gameStart = true;

    //public EditText time;
    public EditText increment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText time = MainMenu.getTime();
        firstPlayerDone = findViewById(R.id.firstPlayerTime);
        firstPlayerDone.setRotation(180);
        secondPlayerDone = findViewById(R.id.secondPlayerTime);

        firstPlayerTimeLeft = Integer.parseInt(time.getText().toString()) * 1000 * 60;
        secondPlayerTimeLeft = Integer.parseInt(time.getText().toString()) * 1000 * 60;

        updateButtonTextFirst();
        updateButtonTextSecond();


        firstPlayerDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(gameStart){
                    gameStart = false;
                    resumeTimerFirst();
                    return;
                }
                if(!firstOnTurn){
                    return;
                }else{
                    resumeTimerSecond();
                }
            }
        });

        secondPlayerDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameStart) {
                    gameStart = false;
                    resumeTimerSecond();
                    return;
                }
                if(firstOnTurn){
                    return;
                }else {
                    resumeTimerFirst();
                }
            }
        });
    }

    private void resumeTimerFirst() {
        firstOnTurn = true;
        if (secondCount != null) {
            secondCount.cancel();
        }

        secondPlayerDone.setBackgroundColor(Color.parseColor("#8F040505"));
        firstPlayerDone.setBackgroundColor(Color.WHITE);
        firstCount = new CountDownTimer(firstPlayerTimeLeft, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                firstPlayerTimeLeft = millisUntilFinished;
                updateButtonTextFirst();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void resumeTimerSecond(){
        firstOnTurn = false;
        if(firstCount != null) {
            firstCount.cancel();
        }
        firstPlayerDone.setBackgroundColor(Color.parseColor("#8F040505"));
        secondPlayerDone.setBackgroundColor(Color.WHITE);
        secondCount = new CountDownTimer(secondPlayerTimeLeft, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondPlayerTimeLeft = millisUntilFinished;
                updateButtonTextSecond();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateButtonTextFirst(){
        int minutes = (int) (firstPlayerTimeLeft / 1000) / 60;
        int seconds = (int) (firstPlayerTimeLeft / 1000) % 60;

        String setTextTo = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        firstPlayerDone.setText(setTextTo);
    }

    private void updateButtonTextSecond(){
        int minutes = (int) (secondPlayerTimeLeft / 1000) / 60;
        int seconds = (int) (secondPlayerTimeLeft / 1000) % 60;

        String setTextTo = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        secondPlayerDone.setText(setTextTo);
    }
}
