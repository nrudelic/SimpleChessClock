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
    private CountDownTimer done;

    private boolean gameStart = true;

    private int increment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int timeMinutes = MainMenu.getTimeMinutes();
        Log.i(TAG, "onCreate: timeMinutes:" + timeMinutes);
        int timeSeconds = MainMenu.getTimeSeconds();

        firstPlayerDone = findViewById(R.id.firstPlayerTime);
        firstPlayerDone.setRotation(180);
        secondPlayerDone = findViewById(R.id.secondPlayerTime);

        firstPlayerTimeLeft = timeMinutes * 1000 * 60 + timeSeconds * 1000;
        secondPlayerTimeLeft = timeMinutes * 1000 * 60 + timeSeconds * 1000;

        updateButtonTextFirst(0);
        updateButtonTextSecond(0);

        increment = MainMenu.getIncrement() * 1000;

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
        updateButtonTextSecond(increment);
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
                updateButtonTextFirst(0);
            }

            @Override
            public void onFinish() {
                firstPlayerDone.setClickable(false);
                highlightLoser(firstPlayerDone);
            }
        }.start();
    }

    private void resumeTimerSecond(){
        updateButtonTextFirst(increment);
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
                updateButtonTextSecond(0);
            }

            @Override
            public void onFinish() {
                secondPlayerDone.setClickable(false);
                highlightLoser(secondPlayerDone);
            }
        }.start();
    }

    private void updateButtonTextFirst(int inc){
        int minutes = (int) ((firstPlayerTimeLeft + inc) / 1000) / 60;
        int seconds = (int) ((firstPlayerTimeLeft + inc) / 1000) % 60;

        String setTextTo = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        firstPlayerDone.setText(setTextTo);
    }

    private void updateButtonTextSecond(int inc){
        int minutes = (int) ((secondPlayerTimeLeft + inc) / 1000) / 60;
        int seconds = (int) ((secondPlayerTimeLeft + inc) / 1000) % 60;

        String setTextTo = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        secondPlayerDone.setText(setTextTo);
    }
    private void highlightLoser(Button button){

        button.setBackgroundColor(Color.RED);
        try {
            wait(10000);
        }catch(Exception e){

        }
    }

}
