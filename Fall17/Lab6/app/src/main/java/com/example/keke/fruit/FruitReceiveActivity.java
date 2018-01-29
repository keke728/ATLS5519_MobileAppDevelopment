package com.example.keke.fruit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FruitReceiveActivity extends AppCompatActivity {

    public void loadWeb(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(fruitImageURL));
        startActivity(intent);
    }


    private String fruitName;
    private String fruitImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_receive);

        Intent intent = getIntent();
        fruitName = intent.getStringExtra("fruitName");
        fruitImageURL = intent.getStringExtra("fruitImageURL");
        Log.i("fruit received",fruitName);
        Log.i("url received", fruitImageURL);

        TextView messageView = (TextView)findViewById(R.id.fruit_suggestion);
        messageView.setText("Ths fruit for you is:   " + fruitName);

        final Button button = (Button)findViewById(R.id.button2);
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                loadWeb(view);
            }
        };
        button.setOnClickListener(onclick);
    }
}
