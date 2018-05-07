package com.example.keke.wufinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DetailActivity extends Activity implements SportsDetailFragment.ButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get reference to the detail fragment
        SportsDetailFragment sportsDetailFragment = (SportsDetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        //get the id passed in the intent
        int typeId = (int) getIntent().getExtras().get("id");
        //pass the type id to the fragment
        sportsDetailFragment.setType(typeId);


    }

    @Override
    public void addactivityclicked(View view) {
        SportsDetailFragment fragment = (SportsDetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
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
