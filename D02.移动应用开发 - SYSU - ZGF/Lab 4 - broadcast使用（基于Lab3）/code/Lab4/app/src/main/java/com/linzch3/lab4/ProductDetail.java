package com.linzch3.lab4;

/**
 * Created by linzch3 on 2017/10/21.
 */

public class ProductDetail {
    private String price, type, info;//价格、类型、信息
    private int imageId;//图片资源

    public ProductDetail(String price, String type, String info, int imageId){
        this.price = price;
        this.type = type;
        this.info = info;
        this.imageId = imageId;
    }

    public String getPrice(){
        return price;
    }
    public String getType(){
        return type;
    }
    public String getInfo(){
        return info;
    }
    public int getImageId(){
        return imageId;
    }
}