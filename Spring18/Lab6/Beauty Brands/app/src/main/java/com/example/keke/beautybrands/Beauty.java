package com.example.keke.beautybrands;

/**
 * Created by keke on 4/2/18.
 */

public class Beauty {
    private String name;
    private int imageID;

    //Constructor
    private Beauty(String newname, int newID){
        this.name = newname;
        this.imageID = newID;
    }

    public static final Beauty[]clarins = {
            new Beauty("Day Cream", R.drawable.cream),
            new Beauty("Eye Cream", R.drawable.eyecream)
    };

    public static final Beauty[]dove = {
            new Beauty("Day Cream", R.drawable.dove_cream),
            new Beauty("Eye Cream", R.drawable.dove_eyecream)
    };

    public static final Beauty[]lancome = {
            new Beauty("Day Cream", R.drawable.lancome_daycream),
            new Beauty("Eye Cream", R.drawable.lancome_eyecream)
    };


    public String getName(){
        return name;
    }

    public int getImageID(){
        return imageID;
    }

    //The String representation of a beauty brand is its name
    public String toString(){
        return this.name;
    }
}
