package com.linzch3.lab3;

/**
 * Created by linzch3 on 2017/10/22.
 */

public class ShoppingCartProduct{

    private String name;
    private String price;

    public ShoppingCartProduct(String name, String price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public String getName_firstLetter(){
        if(name.equals("购物车") && price.equals("价格")){
            return "*";
        }else{
            return String.valueOf(name.charAt(0)).toUpperCase();
        }
    }
    public String getPrice(){
        return price;
    }
}
