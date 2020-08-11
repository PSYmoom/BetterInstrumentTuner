package com.psymoom.betterinstrumenttuner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
//This class includes all of the methods needed to run the tuners and the method to load up the title screen
public class MainActivity extends AppCompatActivity {

    @Override
    //"Main" Method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Makes sure that the buttons send the user to desired page
        final ImageButton guitarTuner = findViewById(R.id.GuitarTuner);
        final ImageButton violinTuner = findViewById(R.id.ViolinTuner);
        final ImageButton Information = findViewById(R.id.Information);
        final Context context = this;

        guitarTuner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, guitar_tuner.class);
                startActivity(intent);
            }
        });

        violinTuner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, violin_tuner.class);
                startActivity(intent);
            }
        });

        Information.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, information.class);
                startActivity(intent);
            }
        });
    }

    //Method to convert the pitch in Hz to its nearest string for the instruments
    public char checkHertz(float pitch, char instrument) {
        switch (instrument){
            //If the instrument is a guitar
            case 'g':
                if (pitch >= 0 && pitch < 96)
                    return 'e';
                else if (pitch >= 96 && pitch < 128)
                    return 'A';
                else if (pitch >= 128 && pitch < 171)
                    return 'D';
                else if (pitch >= 171 && pitch < 221)
                    return 'G';
                else if (pitch >= 221 && pitch < 287.5)
                    return 'B';
                else
                    return 'E';
            //If the instrument is a violin
            case 'v':
                if (pitch >= 0 && pitch < 244)
                    return 'G';
                else if (pitch >= 244 && pitch < 366)
                    return 'D';
                else if (pitch >= 366 && pitch < 550)
                    return 'A';
                else
                    return 'E';
        }
        return 'a';      //The compiler does not compile if I do not have a return statement here
    }

    //Method to check how much the user needs to tune their string to get it perfect
    public String suggestion(char possibleString, int pitch, char instrument) {
        int requiredHertz = 0;

        switch (instrument) {
            //If the instrument is a guitar
            case 'g':
                switch (possibleString) {
                    case 'e':
                        requiredHertz = 82;
                        break;
                    case 'A':
                        requiredHertz = 110;
                        break;
                    case 'D':
                        requiredHertz = 147;
                        break;
                    case 'G':
                        requiredHertz = 196;
                        break;
                    case 'B':
                        requiredHertz = 247;
                        break;
                    case 'E':
                        requiredHertz = 330;
                        break;
                }

                if (pitch >= requiredHertz - 1 && pitch <= requiredHertz + 1)
                    return "Perfect!";
                else if (pitch >= requiredHertz && pitch <= requiredHertz + 5)
                    return "Tune down a little";
                else if (pitch >= requiredHertz)
                    return "Tune down a lot";
                else if (pitch >= requiredHertz - 5)
                    return "Tune up a little";
                else
                    return "Tune up a lot";
            case 'v':
                //If the instrument is a violin
                switch (possibleString) {
                    case 'G':
                        requiredHertz = 196;
                        break;
                    case 'D':
                        requiredHertz = 294;
                        break;
                    case 'A':
                        requiredHertz = 440;
                        break;
                    case 'E':
                        requiredHertz = 659;
                        break;
                }

                if (pitch >= requiredHertz - 1 && pitch <= requiredHertz + 1)
                    return "Perfect!";
                else if (pitch >= requiredHertz && pitch <= requiredHertz + 5)
                    return "Tune down a little";
                else if (pitch >= requiredHertz)
                    return "Tune down a lot";
                else if (pitch >= requiredHertz - 5)
                    return "Tune up a little";
                else
                    return "Tune up a lot";
        }
        return "a";     //The compiler does not compile if I do not have a return statement here
    }
}
