package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class DeadliftActivity extends AppCompatActivity {

    public void nextClick(View view){
        RadioGroup squatGroup = findViewById(R.id.deadliftMenu);
        int buttonID = squatGroup.getCheckedRadioButtonId();

        if(buttonID == R.id.sumo){
            Intent intent = new Intent(DeadliftActivity.this, FormActivity.class);
            intent.putExtra("LIFT", "Sumo Deadlift");
            startActivity(intent);
        }else if(buttonID == R.id.conventional){
            Intent intent = new Intent(DeadliftActivity.this, FormActivity.class);
            intent.putExtra("LIFT", "Conventional Deadlift");
            startActivity(intent);
        }else{
            Log.e("Logic Error: ", "Tried to get button id when a button was not pressed.");
        }
    }

    public void backClick(View view){
        Intent intent = new Intent(DeadliftActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlift);
    }
}
