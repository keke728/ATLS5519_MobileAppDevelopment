package com.example.keke.wufinal;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements SportsListFragment.SportsListListener, SportsDetailFragment.ButtonClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        // get a reference to the frame layout contains SportsDetailFragment
        View fragmentContainer = findViewById(R.id.fragment_container);
        //large layout device
        if(fragmentContainer != null){
            //create new fragment instance
            SportsDetailFragment fragment = new SportsDetailFragment();
            //create new fragment transaction
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            //set the id of the type selected
            fragment.setType(id);
            //replace the fragment in teh fragment container
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            //add fragment to the back stack
            fragmentTransaction.addToBackStack(null);
            //set the transition animation-optional
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //commit the transaction
            fragmentTransaction.commit();
        }else{
            //on a smaller screen
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", (int) id);
            startActivity(intent);
        }
    }


    //to handle back button pressed
    @Override public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() <0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }


    @Override
    public void addactivityclicked(View view) {
        SportsDetailFragment fragment = (SportsDetailFragment)getFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.addActivity();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate menu to add items to the action bar
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //get the ID of the item on the action bar that was clicked
        switch (item.getItemId()){
            case R.id.create_menu:
                //start signup activity
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
