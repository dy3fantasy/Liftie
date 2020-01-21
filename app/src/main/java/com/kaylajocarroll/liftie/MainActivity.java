package com.kaylajocarroll.liftie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Lift lift = new Lift();

    /**
     * Checks if an editText is empty
     * @param etText: an edit text box
     * @return true if etText is empty
     */
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    /**
     * If flag is true set form to visible
     * If flag is false hide form
     * @param flag: if true turn form visible
     */
    protected void toggleForm(boolean flag){
        TextView form1 = findViewById(R.id.sets);
        TextView form2 = findViewById(R.id.reps);
        TextView form3 = findViewById(R.id.weight);
        EditText ef1 = findViewById(R.id.editSets);
        EditText ef2 = findViewById(R.id.editReps);
        EditText ef3 = findViewById(R.id.editWeight);
        Button save = findViewById(R.id.save);

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
        final RadioGroup mainMenu = findViewById(R.id.radioGroup);
        final RadioGroup squatMenu1 = findViewById(R.id.squatMenu1);
        final RadioGroup squatMenu2 = findViewById(R.id.squatMenu2);
        final RadioGroup deadliftMenu = findViewById(R.id.deadliftMenu);
        final EditText accessoryName = findViewById(R.id.editText);
        final EditText editSets = findViewById(R.id.editSets);
        final EditText editReps = findViewById(R.id.editReps);
        final EditText editWeight = findViewById(R.id.editWeight);
        final TextView backArrow = findViewById(R.id.backarrow);
        final TextView question = findViewById(R.id.question);
        final TextView greeting = findViewById(R.id.greeting);
        final Button next = findViewById(R.id.next);
        final Button add = findViewById(R.id.add);
        final Button export = findViewById(R.id.export);
        Button button1 = findViewById(R.id.radioButton1);
        Button button2 = findViewById(R.id.radioButton2);
        Button button3 = findViewById(R.id.radioButton3);
        Button button4 = findViewById(R.id.radioButton4);
        Button sbutton5 = findViewById(R.id.squatButton1);
        Button sbutton6 = findViewById(R.id.squatButton2);
        Button sbutton7 = findViewById(R.id.squatButton3);
        Button sbutton8 = findViewById(R.id.squatButton4);
        Button dbutton9 = findViewById(R.id.deadliftButton1);
        Button dbutton10 = findViewById(R.id.deadliftButton2);
        Button save = findViewById(R.id.save);

        //Main Menu Buttons
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

        //Squat Menu 1 Buttons
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

        //Squat Menu 2 Buttons
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

        //Deadlift Menu Buttons
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

        //Back Arrow
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

        //Next Button (for accessories)
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

        //Save Button (for form)
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
                        greeting.setText(R.string.confirmation);
                        question.setText(R.string.question2);
                    }
                }
            }
        });

        //Add button
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lift.reset();
                mainMenu.setVisibility(View.VISIBLE);
                add.setVisibility(View.INVISIBLE);
                export.setVisibility(View.INVISIBLE);
                greeting.setText(R.string.greeting);
                question.setText(R.string.question1);
            }
        });

        //Export button
        export.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    lift.export(getApplicationContext(),MainActivity.this);
                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error! Cannot export.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
