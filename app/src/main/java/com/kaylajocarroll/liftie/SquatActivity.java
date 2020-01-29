package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class SquatActivity extends AppCompatActivity {

    public void nextClick(View view){
        RadioGroup squatGroup = findViewById(R.id.squatMenu1);
        int buttonID = squatGroup.getCheckedRadioButtonId();

        if(buttonID == R.id.front){
            Intent intent = new Intent(SquatActivity.this, FormActivity.class);
            intent.putExtra("LIFT", "Front Squat");
            startActivity(intent);
        }else if(buttonID == R.id.back){
            Intent intent = new Intent (SquatActivity.this, BackSquatActivity.class);
            startActivity(intent);
        }else{
            Log.e("Logic Error: ", "Tried to get button id when a button was not pressed.");
        }
    }

    public void backClick(View view){
        Intent intent = new Intent(SquatActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void menuClick(View view){
        findViewById(R.id.next).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);
    }
}
