package com.example.keke.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ReceiveBurrito extends AppCompatActivity {

    private String myShop;
    private String myURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_burrito);

        Intent intent = getIntent();
        myShop = intent.getStringExtra("shop");
        myURL = intent.getStringExtra("url");

        TextView textView = (TextView)findViewById(R.id.textSuggestion);
        textView.setText("You should check out " + myShop);
    }
    void getStore(View view){
        loadWeb(view);
    }

    public void loadWeb(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(myURL));
        startActivity(intent);
    }
}
