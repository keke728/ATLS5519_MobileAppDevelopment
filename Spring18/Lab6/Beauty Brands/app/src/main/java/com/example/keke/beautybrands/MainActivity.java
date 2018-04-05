package com.example.keke.beautybrands;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String brandname = (String)listView.getItemAtPosition(position);
                //Create new intent
                Intent intent = new Intent(MainActivity.this, BrandDetailActivity.class);
                //Add brandname to intent
                intent.putExtra("brandname", brandname);
                //Start Intent
                startActivity(intent);
            }
        };
        //Get the list view
        ListView listView = findViewById(R.id.listView);
        //Add listener to the list view
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate menu to add items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Get the ID of the item on the action bar that was clicked
        switch (item.getItemId()){
            case R.id.create_love:
                //start love activity
                Intent intent = new Intent(this, LoveActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
