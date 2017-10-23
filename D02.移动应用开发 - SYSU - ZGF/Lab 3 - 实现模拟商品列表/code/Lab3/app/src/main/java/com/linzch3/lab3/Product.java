package com.linzch3.lab3;

/**
 * Created by linzch3 on 2017/10/21.
 */

public class Product {
    private String name;

    public Product(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getName_firstLetter(){
        return  String.valueOf(name.charAt(0)).toUpperCase();
    }
}
