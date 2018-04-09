package com.example.keke.phones;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements PhoneListFragment.PhoneListListener, PhoneDetailFragment.ButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //create a new fragment when user clicks
    @Override
    public void itemClicked(long id){
        //get a reference to the frame layout that contains PhoneDetailFragment
        View fragmentContainer = findViewById(R.id.fragment_container);
        //large layout device
        if(fragmentContainer != null){
            //create new fragment instance
            PhoneDetailFragment frag = new PhoneDetailFragment();
            //set the id of the platform selected
            frag.setPlatform(id);
            //create new fragment transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //replace the fragment in the fragment container
            ft.replace(R.id.fragment_container,frag);
            //add fragment to the back stack
            ft.addToBackStack(null);
            //set the transition animation-optional
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //commit the transaction
            ft.commit();
        }else{ //app running on a smaller screen
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", (int)id);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void addphoneclicked(View view) {
        PhoneDetailFragment fragment = (PhoneDetailFragment)getFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.addphone();
    }
}
