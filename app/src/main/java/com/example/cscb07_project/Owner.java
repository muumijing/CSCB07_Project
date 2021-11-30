package com.example.cscb07_project;

public class Owner {

    public String username;
    public String email;
    public String password;
    public String login = "false";
    public String locked = "false";
    // public static int num = 0;


    public Owner(){};
    public Owner(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        // num++;
    }



}
