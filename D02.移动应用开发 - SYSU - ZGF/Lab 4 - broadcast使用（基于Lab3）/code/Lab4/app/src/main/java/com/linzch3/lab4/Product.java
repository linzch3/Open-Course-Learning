package com.linzch3.lab4;

/**
 * Created by linzch3 on 2017/10/21.
 */

public class Product {
    private String name, price;

    public Product(String name, String price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    public String getName_firstLetter(){
        return  String.valueOf(name.charAt(0)).toUpperCase();
    }
}
