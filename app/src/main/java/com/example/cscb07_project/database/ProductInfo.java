package com.example.cscb07_project.database;

public class ProductInfo {
    private String storeName;//产品所属店铺
    private String productName;//产品
    private Double productPrice;//产品价格

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public String getProductPriceString(){
        Double price = getProductPrice();
        String PriceString = ((Double) price).toString();
        return PriceString;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
