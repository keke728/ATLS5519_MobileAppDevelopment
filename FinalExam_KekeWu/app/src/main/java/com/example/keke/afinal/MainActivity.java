package com.example.keke.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private BurritoPlace myShop = new BurritoPlace();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void treatMe(View view) {
        //EditText
        EditText editText = (EditText) findViewById(R.id.editText);

        //Switch
        Switch gluten = (Switch) findViewById(R.id.switch1);

        //ToggleButton
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        //RadioGroup
        RadioGroup type = (RadioGroup) findViewById(R.id.radioGroup);

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //CheckBox
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.salsaCheckBox);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.sourcreamCheckBox);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.cheeseCheckBox);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.guacamoleCheckBox);

        //Textview
        TextView textView = (TextView) findViewById(R.id.textBuild);


        String foodName = editText.getText().toString();
        String veggie = "";
        String glutenFree = "";
        String treattype = "";
        String checkedThing = "";
        String location = "";

        int locate = spinner.getSelectedItemPosition();
        switch (locate) {
            case 0:
                location = "The Hills";
                break;
            case 1:
                location = "29th Street";
                break;
            case 2:
                location = "Pearl Street";
                break;
            default:
                location = "The Hills";
                break;
        }

        boolean isveggie = toggleButton.isChecked();
        if (isveggie) {
            veggie = " veggie";
        } else {
            veggie = "  meat";
        }

        boolean isglutenfree = gluten.isChecked();
        if (isglutenfree) {
            glutenFree = " gluten free";
        } else {
            glutenFree = " ";
        }

        int types = type.getCheckedRadioButtonId();
        switch (types) {
            case -1:
                treattype = "burrito";
                break;
            case R.id.radioButton:
                treattype = "buritto";
                break;
            case R.id.radioButton2:
                treattype = "taco";
                break;
        }

        if (checkBox1.isChecked()) {
            checkedThing += " salsa";
        }
        if (checkBox2.isChecked()) {
            checkedThing += " sour cream";
        }
        if (checkBox3.isChecked()) {
            checkedThing += " cheese";
        }
        if (checkBox4.isChecked()) {
            checkedThing += " guacamole";
        }

        String finalResult = "The " + foodName + " is a " + treattype + veggie + glutenFree +
                " with " + checkedThing + " on " + location + ".";
        textView.setText(finalResult);

        ImageView imageView = (ImageView) findViewById(R.id.foodimage);

        int type_id = type.getCheckedRadioButtonId();
        if (type_id == R.id.radioButton) {
            imageView.setImageResource(R.drawable.burrito);
        } else if (type_id == R.id.radioButton2) {
            imageView.setImageResource(R.drawable.taco);
        }
    }


        public void findShop(View view){
            Spinner locationSpinner = (Spinner)findViewById(R.id.spinner);
            Integer burrito = locationSpinner.getSelectedItemPosition();
            myShop.setBurritoInfo(burrito);
            String suggestedShop = myShop.getBurritoShop();
            String suggestedURL = myShop.getBurritoURL();
            Intent intent = new Intent(this, ReceiveBurrito.class);
            intent.putExtra("shop", suggestedShop);
            intent.putExtra("url", suggestedURL);
            startActivity(intent);
    }

    }

