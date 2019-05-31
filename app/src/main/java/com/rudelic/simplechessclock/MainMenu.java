package com.rudelic.simplechessclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class MainMenu extends AppCompatActivity {
    private Button startButton;

    public static int timeMinutes;
    public static int timeSeconds;
    public static int incrementInt;

    public EditText increment;
    private NumberPicker fullTimeMinutes = null;
    private NumberPicker fullTimeSeconds = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //setting minute picker
        fullTimeMinutes = findViewById(R.id.fullTimeMinutes);
        fullTimeMinutes.setMinValue(0);
        fullTimeMinutes.setMaxValue(9);
        fullTimeMinutes.setValue(5);
        fullTimeMinutes.setWrapSelectorWheel(true);
        String[] minutes = {"0", "1", "2", "3", "4", "5", "10", "15", "30", "60"};
        fullTimeMinutes.setDisplayedValues(minutes);

        //setting seconds picker
        fullTimeSeconds = findViewById(R.id.fullTimeSeconds);
        fullTimeSeconds.setMinValue(0);
        fullTimeSeconds.setMaxValue(3);
        fullTimeSeconds.setValue(0);
        fullTimeSeconds.setWrapSelectorWheel(true);
        String[] seconds = {"0", "15", "30", "45"};
        fullTimeSeconds.setDisplayedValues(seconds);


        increment = findViewById(R.id.increment);
            //setting button Start
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Should increment happen?
                if(increment.getText().toString().compareTo("") == 0){
                    incrementInt = 0;
                } else {
                    incrementInt = Integer.parseInt(increment.getText().toString());
                }

                timeMinutes = fullTimeMinutes.getValue();
                timeSeconds = fullTimeSeconds.getValue();
                if(timeMinutes == 0 && timeSeconds == 0) {
                    //do nothing
                } else {
                    startActivity(new Intent(MainMenu.this, MainActivity.class));
                }
            }
        });

    }

    public static int getTimeMinutes(){
        switch(timeMinutes) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 10;
            case 7:
                return 15;
            case 8:
                return 30;
            default:
                return 60;
        }
    }
    public static int getTimeSeconds() {
        switch(timeSeconds) {
            case 0:
                return 0;
            case 1:
                return 15;
            case 2:
                return 30;
            default:
                return 45;
        }
    }
    public static int getIncrement(){
        return incrementInt;
    }
}
