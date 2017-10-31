package com.linzch3.lab5;

/**
 * Created by linzch3 on 2017/10/26.
 */

public class PlaceOrderEvent {
    private String name;
    private String price;

    public PlaceOrderEvent(String name, String price){
        this.name = name;
        this.price = price;
    }
    public String getProductName_firstLetter(){
        return String.valueOf(name.charAt(0)).toUpperCase();
    }
    public String getProductName(){
        return name;
    }
    public String getProductPrice(){
        return price;
    }
}
