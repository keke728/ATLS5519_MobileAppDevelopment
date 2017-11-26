package com.example.keke.fruit;

/**
 * Created by keke on 11/21/17.
 */

public class FruitInfo {
    private String fruitName;
    private String fruitImageURL;


    private void setFruitChoose(Integer fruitColor){
        switch(fruitColor){
            case 0:
                fruitName="Strawberry";
                fruitImageURL="https://en.wikipedia.org/wiki/Strawberry";
                break;
            case 1:
                fruitName="Banana";
                fruitImageURL="https://en.wikipedia.org/wiki/Banana";
                break;
            case 2:
                fruitName="Watermelon";
                fruitImageURL="https://en.wikipedia.org/wiki/Watermelon";
                break;
            case 3:
                fruitName="Grape";
                fruitImageURL="https://en.wikipedia.org/wiki/Grape";
                break;
            case 4:
                fruitName="Coconut";
                fruitImageURL="https://en.wikipedia.org/wiki/Coconut";
                break;
            default:
                fruitName="Apple";
                fruitImageURL="https://en.wikipedia.org/wiki/Apple";
                break;
        }
    }

    public void setFruitName(Integer fruitColor){
        setFruitChoose(fruitColor);
    }

    public void setFruitImageURL(Integer fruitColor){
        setFruitChoose(fruitColor);
    }

    public String getFruitName(){
        return fruitName;
    }

    public String getFruitImageURL(){
        return fruitImageURL;
    }

}
