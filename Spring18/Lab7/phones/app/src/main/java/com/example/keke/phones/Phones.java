package com.example.keke.phones;

import java.util.ArrayList;
import java.util.Arrays;

public class Phones {

    private String platform;
    private ArrayList<String>mobilephones = new ArrayList<>();

    //constructor
    private Phones(String pho, ArrayList<String>phones){
        this.platform = pho;
        this.mobilephones = new ArrayList<String>(phones);
    }

    public static final Phones[]phones = {
            new Phones("iPhone", new ArrayList<String>(Arrays.asList("iPhone X", "iPhone 8", "iPhone 8 Plus", "iPhone SE"))),
            new Phones("Android", new ArrayList<String>(Arrays.asList("Samsung", "Nexus", "Huawei", "ZTE")))
    };

    public String getPlatform(){
        return platform;
    }

    public ArrayList<String> getMobilephones(){
        return mobilephones;
    }

    public String toString(){
        return this.platform;
    }
}
