package com.example.cscb07_project;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public String storeName;
    public String owner;
    public List<Product> products_inventory;
    public List<String> orders;


    public Store(){

        products_inventory = new ArrayList<Product>();
        orders = new ArrayList<String>();
    }
    public Store(String storeName) {
        this();
        this.storeName = storeName;
    }
    public String toString() {

        return storeName;
    }
}
