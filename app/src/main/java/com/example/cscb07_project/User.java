package com.example.cscb07_project;

import java.util.ArrayList;

public class User {
    public String username;
    public String email;
    public String password;
    public String login = "false";
    public String locked = "false";
    // public static int num = 0;
    public ArrayList<String> allOrders = new ArrayList<String>();
    public ArrayList<String> ordersCompleted = new ArrayList<String>();
    public ArrayList<String> ordersInProcess = new ArrayList<String>();

    public User(){};
    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        // num++;
    }

    public void addOrder(String order){
        allOrders.add(order);
        ordersInProcess.add(order);
    }

    public void removeOrder(String order){
        for(String s: ordersInProcess){
            if(s.equals(order)){
                allOrders.remove(order);
            }
        }
        ordersCompleted.add(order);
    }


}
