package com.example.keke.phones;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Phones {

    private String platform;
    private ArrayList<String> mobilephones = new ArrayList<>();

    //constructor
    public Phones(String pho, ArrayList<String> phones){
        this.platform = pho;
        this.mobilephones = new ArrayList<String>(phones);
    }

    public static ArrayList<Phones> phones = new ArrayList<Phones>();

    public String getPlatform(){
        return platform;
    }

    public ArrayList<String> getMobilephones(){
        return mobilephones;
    }

    public String toString(){
        return this.platform;
    }

    //Data Persistence
    public void storePhones(Context context, long platformId){
        //get access to a shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences("MobilePhones", Context.MODE_PRIVATE);
        //create an editor to write to the shared preferences file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //create a set
        Set<String> set = new HashSet<String>();
        //add phones to the set
        set.addAll(phones.get((int) platformId).getMobilephones());
        //pass the key/value pair to the shared preference file
        editor.putStringSet(phones.get((int)platformId).getPlatform(), set);
        //save changes
        editor.commit();

    }
}
