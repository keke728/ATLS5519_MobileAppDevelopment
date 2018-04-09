package com.example.keke.phones;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DetailActivity extends Activity implements PhoneDetailFragment.ButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get reference to the phone detail fragment
        PhoneDetailFragment phoneDetailFragment = (PhoneDetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        //get the id passed in the intent
        int platformId = (int)getIntent().getExtras().get("id");
        //pass the platform id to the fragment
        phoneDetailFragment.setPlatform(platformId);
    }

    @Override
    public void addphoneclicked(View view) {
        PhoneDetailFragment fragment = (PhoneDetailFragment)getFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.addphone();
    }
}
