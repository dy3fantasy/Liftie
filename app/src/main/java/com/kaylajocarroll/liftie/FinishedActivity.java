package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FinishedActivity extends AppCompatActivity {

    public void addClick(View view){
        Intent intent = new Intent(FinishedActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void exportClick(View view){
        Intent intent = getIntent();
        Lift lift = (Lift) intent.getParcelableExtra("LIFT");
        //Uri uri = Uri.parse(intent.getStringExtra("URI"));
        //lift.setUri(uri);
        try {
            lift.export(getApplicationContext(),FinishedActivity.this);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(FinishedActivity.this, "Error! Cannot export.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
    }
}
