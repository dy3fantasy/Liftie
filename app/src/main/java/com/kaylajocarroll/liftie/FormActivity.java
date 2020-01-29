package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class FormActivity extends AppCompatActivity {
    Lift lift = new Lift();

    public void saveClick(View view){
        EditText editSets = findViewById(R.id.editSets);
        EditText editReps = findViewById(R.id.editReps);
        EditText editWeight = findViewById(R.id.editWeight);
        Intent intent = getIntent();

        if(isEmpty(editSets)){
            Toast.makeText(FormActivity.this, "Please enter # sets." , Toast.LENGTH_SHORT).show();
        }else if(isEmpty(editReps)){
            Toast.makeText(FormActivity.this, "Please enter # reps." , Toast.LENGTH_SHORT).show();
        }else if(isEmpty(editWeight)){
            Toast.makeText(FormActivity.this, "Please enter weight." , Toast.LENGTH_SHORT).show();
        }else{
            lift.setName(intent.getStringExtra("LIFT"));
            lift.setSets(editSets.getText().toString());
            lift.setReps(editReps.getText().toString());
            lift.setWeight(editWeight.getText().toString());
            if(lift.save(FormActivity.this)) {
                //lift.setUri(getApplicationContext());
                Log.e("MyExternalFile in form act: ", lift.myExternalFile.toString());
                Toast.makeText(FormActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                Intent finishedIntent = new Intent(FormActivity.this, FinishedActivity.class);
                finishedIntent.putExtra("LIFT", lift);
                //finishedIntent.putExtra("URI", (lift.getUri()).toString());
                startActivity(finishedIntent);
            }
        }
    }

    public void backClick(View view){
        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Checks if an editText is empty
     * @param etText: an edit text box
     * @return true if etText is empty
     */
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }
}
