package com.example.keke.beautybrands;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class LoveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        //Get reference to action bar
        ActionBar actionBar = getActionBar();
        //Enable the up button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
