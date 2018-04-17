package com.example.keke.babyface.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.keke.babyface.Data.ImageAssets;
import com.example.keke.babyface.R;

//This activity will display a custom Android image composed of three body parts: head, body, and legs
public class MainActivity extends AppCompatActivity {

    //Create a layout file that displays one body part image named fragment_body_part.xml
      //This layout contain a single ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {

            //Create a new BodyPartFragment instance and display it using the FragmentManager
            BodyPartFragment headFragment = new BodyPartFragment();
            BodyPartFragment bodyFragment = new BodyPartFragment();
            BodyPartFragment legFragment = new BodyPartFragment();

            //Set the list of image ids for the head fragment and set the position to the second image in the list
            headFragment.setImageIds(ImageAssets.getHeads());
            bodyFragment.setImageIds(ImageAssets.getBodies());
            legFragment.setImageIds(ImageAssets.getLegs());
            headFragment.setmListIndex(1);
            bodyFragment.setmListIndex(1);
            legFragment.setmListIndex(1);

            //Use a FragmentManager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            //Fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
