package com.example.cscb07_project;

import java.io.Serializable;

public class CustomProductView implements Serializable {
    private String storeName;//产品所属店铺

    private String ProductName;
    //product的名字
    private Integer ProductQuantity;
    //product的数量
    private Double ProductPrice;

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
