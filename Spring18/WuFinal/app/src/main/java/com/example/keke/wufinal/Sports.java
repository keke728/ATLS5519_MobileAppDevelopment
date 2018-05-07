package com.example.keke.wufinal;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sports {

    private String type;
    private ArrayList<String> Activities = new ArrayList<>();

    //constructor
    private Sports(String types, ArrayList<String> activities){
        this.type = types;
        this.Activities = new ArrayList<String>(activities);
    }

    public static final Sports[] sports = {
            new Sports("Cardio", new ArrayList<String>()),
            new Sports("Strength", new ArrayList<String>()),
            new Sports("Flexibility", new ArrayList<String>())
    };

    public void storeSports(Context context, long typeId){
        // get access to a shared preferences file
        SharedPreferences sharedPrefs = context.getSharedPreferences("SportsActivity", Context.MODE_PRIVATE);
        // create an editor to write to the shared preferences file
        SharedPreferences.Editor editor = sharedPrefs.edit();
        // create a set
        Set<String> set = new HashSet<String>();
        // add sports to the set
        set.addAll(sports[(int) typeId].getActivities());
        // pass the key/value pair to the shared preference file
        editor.putStringSet(sports[(int) typeId].getType(),set);
        // save changes
        editor.commit();
    }

    public void loadSports(Context context, int typeId){
        // get access to a shared preferences file
        SharedPreferences sharedPres = context.getSharedPreferences("SportsActivity", Context.MODE_PRIVATE);
        // create an editor to read from the shared preferences file
        SharedPreferences.Editor editor = sharedPres.edit();
        // create a set with the Sport list
        Set<String> set = sharedPres.getStringSet(sports[typeId].getType(), null);
        // if there was a saved list add it to the Sports array
        if (set != null){
            Sports.sports[typeId].Activities.addAll(set);
        }
        // if no sport list was saved, use the default
        else {
            switch (typeId){
                case 0:
                    Sports.sports[0].Activities.addAll(Arrays.asList("Running", "Cycling", "Hiking"));
                    break;
                case 1:
                    Sports.sports[1].Activities.addAll(Arrays.asList("Weight Lifting", "Speed Strength", "Muscle Power"));
                    break;
                case 2:
                    Sports.sports[2].Activities.addAll(Arrays.asList("Yoga", "Walk", "Dance"));
                    break;
                default:
                    Sports.sports[0].Activities.addAll(Arrays.asList("Running", "Cycling", "Hiking"));
                    break;
            }
        }
    }

    public String getType(){
        return type;
    }

    public ArrayList<String> getActivities(){
        return Activities;
    }

    public String toString() {
        return this.type;
    }
}
