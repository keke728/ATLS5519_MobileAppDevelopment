package com.example.keke.beautybrands;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class BrandDetailActivity extends ListActivity {

    private String brandname;

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        Intent intent = new Intent(BrandDetailActivity.this,ProductActivity.class);
        intent.putExtra("product", (int)id);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        brandname = i.getStringExtra("brandname");
        //Get the list view
        ListView listBrand = getListView();
        //Define an array adapter
        ArrayAdapter<Beauty>listAdapter;
        //Initialize the array adapter with the right list of brands
        switch (brandname){
            case"Clarins":
                listAdapter = new ArrayAdapter<Beauty>(this, android.R.layout.simple_list_item_1, Beauty.clarins);
                break;
            case"Dove":
                listAdapter = new ArrayAdapter<Beauty>(this, android.R.layout.simple_list_item_1, Beauty.dove);
                break;
            case"Lancome":
                listAdapter = new ArrayAdapter<Beauty>(this, android.R.layout.simple_list_item_1, Beauty.lancome);
                break;
            default:listAdapter = new ArrayAdapter<Beauty>(this, android.R.layout.simple_list_item_1, Beauty.clarins);
        }
        //Set the array adapter on the list view
        listBrand.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu to add items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get the ID of the item on the action bar that was clicked
        switch (item.getItemId()) {
            case R.id.create_love:
                //start order activity
                Intent intent = new Intent(this, LoveActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
