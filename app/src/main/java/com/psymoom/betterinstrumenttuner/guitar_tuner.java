package com.psymoom.betterinstrumenttuner;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
//This class exists to load up the guitar tuner
public class guitar_tuner extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Displays the required screen from layout file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guitar_tuner);

        //Initializes the microphone for user input and text boxes for output on screen
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);
        final TextView possibleHertz = findViewById(R.id.possibleHertzGuitar);;
        final TextView possibleString = findViewById(R.id.possibleStringGuitar);
        final TextView suggestion = findViewById(R.id.suggestionGuitar);

        //Thread provided by the package Tarsos DPS to take sound input and convert it to hertz
        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult inputSound, AudioEvent e) {
                final int inputHertz = (int) inputSound.getPitch();   //Method which converts input sound to its pitch in hertz
                if (inputHertz != -1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Sets the text boxes to display according to the hertz that was played
                            possibleHertz.setText("" + inputHertz);
                            possibleString.setText("" + checkHertz(inputHertz, 'g'));
                            suggestion.setText("" + suggestion(checkHertz(inputHertz, 'g'), inputHertz, 'g'));
                        }
                    });
                }
            }
        };
        //Tells the package which algorithm to use, what the sample size is and what the input buffer size should be
        AudioProcessor p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        //Sets the input of this process to the microphone
        dispatcher.addAudioProcessor(p);
        //Starts the thread
        new Thread(dispatcher).start();
    }
}
