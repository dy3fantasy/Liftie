package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AccessoryActivity extends AppCompatActivity {

    public void nextClick(View view){
        EditText accessoryName = findViewById(R.id.accessoryName);

        if(isEmpty(accessoryName)){
            Toast.makeText(AccessoryActivity.this, "Please enter accessory name." , Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(AccessoryActivity.this, FormActivity.class);
            intent.putExtra("LIFT", accessoryName.getText().toString());
            startActivity(intent);
        }
    }

    public void backClick(View view){
        Intent intent = new Intent(AccessoryActivity.this, MainActivity.class);
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
        setContentView(R.layout.activity_accessory);
    }
}
