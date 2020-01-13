package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Lift lift = new Lift();

    /**
     * Checks if an editText is empty
     * @param etText
     * @return
     */
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    /**
     * If flag is true set form to visible
     * If flag is false hide form
     * @param flag
     */
    protected void toggleForm(boolean flag){
        TextView form1 = (TextView) findViewById(R.id.sets);
        TextView form2 = (TextView) findViewById(R.id.reps);
        TextView form3 = (TextView) findViewById(R.id.weight);
        EditText ef1 = (EditText) findViewById(R.id.editSets);
        EditText ef2 = (EditText) findViewById(R.id.editReps);
        EditText ef3 = (EditText) findViewById(R.id.editWeight);
        Button save = (Button) findViewById(R.id.save);

        if(flag){
            form1.setVisibility(View.VISIBLE);
            form2.setVisibility(View.VISIBLE);
            form3.setVisibility(View.VISIBLE);
            ef1.setVisibility(View.VISIBLE);
            ef2.setVisibility(View.VISIBLE);
            ef3.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
            ef1.setText("");
            ef2.setText("");
            ef3.setText("");
        }else{
            form1.setVisibility(View.INVISIBLE);
            form2.setVisibility(View.INVISIBLE);
            form3.setVisibility(View.INVISIBLE);
            ef1.setVisibility(View.INVISIBLE);
            ef2.setVisibility(View.INVISIBLE);
            ef3.setVisibility(View.INVISIBLE);
            save.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioGroup mainMenu = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioGroup squatMenu1 = (RadioGroup) findViewById(R.id.squatMenu1);
        final RadioGroup squatMenu2 = (RadioGroup) findViewById(R.id.squatMenu2);
        final RadioGroup deadliftMenu = (RadioGroup) findViewById(R.id.deadliftMenu);
        final EditText accessoryName = (EditText) findViewById(R.id.editText);
        final EditText editSets = (EditText) findViewById(R.id.editSets);
        final EditText editReps = (EditText) findViewById(R.id.editReps);
        final EditText editWeight = (EditText) findViewById(R.id.editWeight);
        Button button1 = (Button) findViewById(R.id.radioButton1);
        Button button2 = (Button) findViewById(R.id.radioButton2);
        Button button3 = (Button) findViewById(R.id.radioButton3);
        Button button4 = (Button) findViewById(R.id.radioButton4);
        Button sbutton5 = (Button) findViewById(R.id.squatButton1);
        Button sbutton6 = (Button) findViewById(R.id.squatButton2);
        Button sbutton7 = (Button) findViewById(R.id.squatButton3);
        Button sbutton8 = (Button) findViewById(R.id.squatButton4);
        Button dbutton9 = (Button) findViewById(R.id.deadliftButton1);
        Button dbutton10 = (Button) findViewById(R.id.deadliftButton2);
        final Button next = (Button) findViewById(R.id.next);
        final TextView backArrow = (TextView) findViewById(R.id.backarrow);
        Button save = (Button) findViewById(R.id.save);
        final Button add = (Button) findViewById(R.id.add);
        final Button export = (Button) findViewById(R.id.export);
        final TextView question = (TextView) findViewById(R.id.question);
        final TextView greeting = (TextView) findViewById(R.id.greeting);

        /**
         * Main Menu Buttons
         */
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainMenu.setVisibility(View.INVISIBLE);
                squatMenu1.setVisibility(View.VISIBLE);
                backArrow.setVisibility(View.VISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainMenu.setVisibility(View.INVISIBLE);
                lift.setName("Bench");
                toggleForm(true);
                backArrow.setVisibility(View.VISIBLE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainMenu.setVisibility(View.INVISIBLE);
                deadliftMenu.setVisibility(View.VISIBLE);
                backArrow.setVisibility(View.VISIBLE);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainMenu.setVisibility(View.INVISIBLE);
                accessoryName.setVisibility(View.VISIBLE);
                backArrow.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });

        /**
         * Squat Menu 1 Buttons
         */
        sbutton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.setName("Front Squat");
                squatMenu1.setVisibility(View.INVISIBLE);
                toggleForm(true);
            }
        });
        sbutton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                squatMenu1.setVisibility(View.INVISIBLE);
                squatMenu2.setVisibility(View.VISIBLE);
            }
        });

        /**
         * Squat Menu 2 Buttons
         */
        sbutton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.setName("HB Squat");
                squatMenu2.setVisibility(View.INVISIBLE);
                toggleForm(true);
            }
        });
        sbutton8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.setName("LB Squat");
                squatMenu2.setVisibility(View.INVISIBLE);
                toggleForm(true);
            }
        });

        /**
         * Deadlift Menu Buttons
         */
        dbutton9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.setName("Sumo Deadlift");
                deadliftMenu.setVisibility(View.INVISIBLE);
                toggleForm(true);
            }
        });
        dbutton10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.setName("Conventional Deadlift");
                deadliftMenu.setVisibility(View.INVISIBLE);
                toggleForm(true);
            }
        });

        /**
         * Back Arrow
         */
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                squatMenu1.setVisibility(View.INVISIBLE);
                squatMenu2.setVisibility(View.INVISIBLE);
                deadliftMenu.setVisibility(View.INVISIBLE);
                accessoryName.setVisibility(View.INVISIBLE);
                backArrow.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                toggleForm(false);
                lift.reset();
                mainMenu.setVisibility(View.VISIBLE);
            }
        });

        /**
         * Next Button (for accessories)
         */
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(accessoryName)){
                    Toast.makeText(MainActivity.this, "Please enter accessory name." , Toast.LENGTH_LONG).show();
                }else {
                    lift.setName(accessoryName.getText().toString());
                    accessoryName.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    toggleForm(true);
                }
            }
        });

        /**
         * Save Button (for form)
         */
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isEmpty(editSets)){
                    Toast.makeText(MainActivity.this, "Please enter # sets." , Toast.LENGTH_SHORT).show();
                }else if(isEmpty(editReps)){
                    Toast.makeText(MainActivity.this, "Please enter # reps." , Toast.LENGTH_SHORT).show();
                }else if(isEmpty(editWeight)){
                    Toast.makeText(MainActivity.this, "Please enter weight." , Toast.LENGTH_SHORT).show();
                }else{
                    lift.setSets(editSets.getText().toString());
                    lift.setReps(editReps.getText().toString());
                    lift.setWeight(editWeight.getText().toString());
                    if(lift.save(MainActivity.this)) {
                        Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        toggleForm(false);
                        add.setVisibility(View.VISIBLE);
                        export.setVisibility(View.VISIBLE);
                        backArrow.setVisibility(View.INVISIBLE);
                        greeting.setText("Got it Kayla,");
                        question.setText("anything else?");
                    }
                }
            }
        });

        /**
         * Add button
         */
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.reset();
                mainMenu.setVisibility(View.VISIBLE);
                add.setVisibility(View.INVISIBLE);
                export.setVisibility(View.INVISIBLE);
                greeting.setText("Hello Kayla,");
                question.setText("what did you do today?");
            }
        });

        /**
         * Export button
         */
        export.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    lift.export(getApplicationContext(),MainActivity.this);
                }catch(IOException ioe){
                    ioe.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error! Cannot export.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
