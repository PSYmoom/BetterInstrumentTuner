package com.psymoom.betterinstrumenttuner;

import android.os.Bundle;
//This class exists to load up the information page
public class information extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Displays the required screen from layout file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
    }
}
