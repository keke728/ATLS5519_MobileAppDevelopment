package com.example.keke.foodies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Switch switch_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findFood(View view) {
        //Toggle Button
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        boolean food = toggle.isChecked();

        //Spinner
        Spinner foodChoose = (Spinner) findViewById(R.id.spinner);
        String vegeOrOmni = String.valueOf(foodChoose.getSelectedItem());

        RadioGroup cost = (RadioGroup) findViewById(R.id.radioGroup);
        int cost_id = cost.getCheckedRadioButtonId();

        String foodForYou = "McDonalds";

        CheckBox americanTaste = (CheckBox)findViewById(R.id.checkBox1);
        Boolean american = americanTaste.isChecked();

        CheckBox internationalTaste = (CheckBox)findViewById(R.id.checkBox2);
        Boolean international = internationalTaste.isChecked();

        switch_bottom = (Switch)findViewById(R.id.switch1);
        switch_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = switch_bottom.isChecked();

                if(check){
                    Toast.makeText(MainActivity.this, "Switch is on", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Switch is off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (cost_id == -1) {
            Context context = getApplicationContext();
            CharSequence text = "Please select a cost level!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            if (food) {
                if (vegeOrOmni.equals("Vegetarian")) {
                    switch (cost_id) {
                        case R.id.radioButton1:
                            if (american) {
                                foodForYou = "Subway";
                            } else {
                                foodForYou = "Panda Express";
                            }
                            break;
                        case R.id.radioButton2:
                            if (american) {
                                foodForYou = "Taste of Philly";
                            } else {
                                foodForYou = "Uncle Jones";
                            }
                            break;
                        default:
                            if (american) {
                                foodForYou = "Cheese Factory";
                            } else {
                                foodForYou = "Kathmandu Restaurant";
                            }
                            break;
                    }
                } else {
                    switch (cost_id) {
                        case R.id.radioButton1:
                            if (american) {
                                foodForYou = "Burger King";
                            } else {
                                foodForYou = "Noodles Company";
                            }
                            break;
                        case R.id.radioButton2:
                            if (american) {
                                foodForYou = "Next Door";
                            } else {
                                foodForYou = "Chinese Buffet";
                            }
                            break;
                        default:
                            if (american) {
                                foodForYou = "Red Lobster";
                            } else {
                                foodForYou = "Arugula";
                            }
                    }
                }

            }else{
                if (cost_id == R.id.radioButton1) {
                    switch (vegeOrOmni) {
                        case "Vegetarian":
                            foodForYou = "Homemade Fries";
                            break;
                        case "Omnivore":
                            foodForYou = "Chiken Taco";
                            break;
                        default:
                            foodForYou = "Mushroom Soup";
                    }
                }else if (cost_id == R.id.radioButton2){
                    switch (vegeOrOmni) {
                        case "Vegetarian":
                            foodForYou = "Quinoa Salad";
                            break;
                        case "Omnivore":
                            foodForYou = "Salisbury Steak";
                            break;
                        default:
                            foodForYou = "Quinoa Salad";
                    }
                }else{
                    switch (vegeOrOmni) {
                        case "Vegetarian":
                            foodForYou = "Organic Sweet Potato Quinoa Salad ";
                            break;
                        case "Omnivore":
                            foodForYou = "Organic Beef Stew";
                            break;
                        default:
                            foodForYou = "Organic Sweet Potato Quinoa Salad";
                    }
                }

            }
        }



            TextView foodSelection = (TextView) findViewById(R.id.foodTextView);
            foodSelection.setText(foodForYou+" is a good choice!");
    }
}
