package com.example.keke.afinal;

/**
 * Created by keke on 12/17/17.
 */

public class BurritoPlace {

    private String burritoShop;
    private String burritoURL;

    void setBurritoInfo(Integer burrito){
        switch (burrito){
            case 0:
                burritoShop = "Illegal Petes";
                burritoURL = "http://illegalpetes.com";
                break;
            case 1:
                burritoShop = "Chipotle";
                burritoURL = "https://www.chipotle.com";
                break;
            case 2:
                burritoShop = "Bartaco";
                burritoURL = "https://bartaco.com";
                break;
            default:
                burritoShop = "Illegal Petes";
                burritoURL = "http://illegalpetes.com";
                break;
        }
    }

    public void setBurritoShop(Integer burrito){
        setBurritoInfo(burrito);
    }

    public void setBurritoURL(Integer burrito){
        setBurritoInfo(burrito);
    }

    public String getBurritoShop(){
        return burritoShop;
    }

    public String getBurritoURL(){
        return  burritoURL;
    }
}
