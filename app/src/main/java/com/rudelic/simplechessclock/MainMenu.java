package com.rudelic.simplechessclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainMenu extends AppCompatActivity {
    private Button startButton;
    public static EditText time;
    public EditText increment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        time = (EditText) findViewById(R.id.fullTime);
        startButton = findViewById(R.id.startButton);
        ((View) startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, MainActivity.class));
            }
        });
    }

    public static EditText getTime(){
        return time;
    }
}
