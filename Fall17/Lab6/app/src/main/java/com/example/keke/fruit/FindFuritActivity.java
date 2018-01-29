package com.example.keke.fruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class FindFuritActivity extends AppCompatActivity {

    private FruitInfo myFruit = new FruitInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_furit);

        final Button button = (Button)findViewById(R.id.button);

        View.OnClickListener onclick = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                findFruit(view);

            }
        };

        button.setOnClickListener(onclick);
    }
        public void findFruit(View view){
            Spinner colorSpinner = (Spinner)findViewById(R.id.spinner);
            Integer color = colorSpinner.getSelectedItemPosition();
            myFruit.setFruitName(color);
            String suggestedFruit = myFruit.getFruitName();
            String suggestedFruitImageURL = myFruit.getFruitImageURL();
            Log.i("fruit",suggestedFruit);
            Log.i("url",suggestedFruitImageURL);

            Intent intent = new Intent(this,FruitReceiveActivity.class);
            intent.putExtra("fruitName",suggestedFruit);
            intent.putExtra("fruitImageURL",suggestedFruitImageURL);
            startActivity(intent);
    }
}
