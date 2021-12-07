package com.example.cscb07_project;

import java.io.Serializable;

public class CustomProductView implements Serializable {
    private String storeName;

    private String ProductName;
    private Integer ProductQuantity;
    private Double ProductPrice;


    public CustomProductView (){

    }

    public CustomProductView(String storeName, String ProductName, Double ProductPrice,Integer ProductQuantity) {
        this.storeName = storeName;
        this.ProductName = ProductName;
        this.ProductQuantity = ProductQuantity;
        this.ProductPrice = ProductPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        ProductQuantity = productQuantity;
    }

    public Double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Double productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductPriceString(){
        Double price = getProductPrice();
        String PriceString = ((Double) price).toString();
        return PriceString;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}
