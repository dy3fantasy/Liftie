package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

//disable landscape view
//look into vector assets:
//change to have bottom action bar
//change ic_launcher mipmap files
public class MainActivity extends AppCompatActivity {

    /**
     * When next is clicked proceed to the next activity based on which button is pressed
     * @param view
     */
    public void nextClick(View view){
        RadioGroup mainMenu = findViewById(R.id.mainMenu);
        int buttonID = mainMenu.getCheckedRadioButtonId();
        //get the ID of the button pressed in the main menu
        if(buttonID == R.id.squat){
            Intent intent = new Intent(MainActivity.this, SquatActivity.class);
            startActivity(intent);
        }else if(buttonID == R.id.bench){
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            intent.putExtra("LIFT","Bench");
            startActivity(intent);
        }else if(buttonID == R.id.deadlift){
            Intent intent = new Intent(MainActivity.this, DeadliftActivity.class);
            startActivity(intent);
        }else if(buttonID == R.id.accessory){
            Intent intent = new Intent(MainActivity.this, AccessoryActivity.class);
            startActivity(intent);
        }else{
            Log.e("Logic Error: ", "Tried to get button id when a button was not pressed.");
        }
    }

    public void menuClick(View view){
        findViewById(R.id.next).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
