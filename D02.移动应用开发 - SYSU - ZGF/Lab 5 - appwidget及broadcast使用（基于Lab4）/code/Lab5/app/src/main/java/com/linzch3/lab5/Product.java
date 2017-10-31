package com.linzch3.lab5;

/**
 * Created by linzch3 on 2017/10/21.
 */

public class Product {
    private String name, price;
    private int imageId;

    public Product(String name, String price, int imageId){
        this.name = name;
        this.price = price;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    public int getImageId(){
        return imageId;
    }
    public String getName_firstLetter(){
        return  String.valueOf(name.charAt(0)).toUpperCase();
    }
}
