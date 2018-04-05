package com.example.keke.beautybrands;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //Get product id from the intent
        int productnum = (Integer)getIntent().getExtras().get("product");
        Beauty beauty = Beauty.clarins[productnum];


        //Populate image
        ImageView productImageView = (ImageView)findViewById(R.id.productImageView);
        productImageView.setImageResource(beauty.getImageID());


        //Populate name
        TextView productName = (TextView)findViewById(R.id.product_name);
        productName.setText(beauty.getName());
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
