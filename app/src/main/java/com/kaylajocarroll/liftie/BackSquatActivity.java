package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class BackSquatActivity extends AppCompatActivity {

    public void nextClick(View view){
        RadioGroup squatGroup = findViewById(R.id.squatMenu2);
        int buttonID = squatGroup.getCheckedRadioButtonId();

        if(buttonID == R.id.lowbar){
            Intent intent = new Intent(BackSquatActivity.this, FormActivity.class);
            intent.putExtra("LIFT", "Low-Bar Back Squat");
            startActivity(intent);
        }else if(buttonID == R.id.highbar){
            Intent intent = new Intent(BackSquatActivity.this, FormActivity.class);
            intent.putExtra("LIFT", "High-Bar Back Squat");
            startActivity(intent);
        }else{
            Log.e("Logic Error: ", "Tried to get button id when a button was not pressed.");
        }
    }

    public void backClick(View view){
        Intent intent = new Intent(BackSquatActivity.this, SquatActivity.class);
        startActivity(intent);
    }

    public void menuClick(View view){
        findViewById(R.id.next).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_squat);
    }
}
