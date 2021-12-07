package com.example.cscb07_project.ownerlogin;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.cscb07_project.StoreOwnerPage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MVPOwnerPresenter implements Contract.MVPOwnerPresenter{
    public Contract.MVPOwnerModel model;
    public Contract.MVPOwnerView view;

    public MVPOwnerPresenter(MVPOwnerModel model, MVPOwnerLogin view){
        this.model = model;
        this.view = view;
    }

    public void checkLoginStatus(){
        String Email = view.getEmail();
        String Password = view.getPassword();

        if(Email.equals("")){
            view.displayMessage("Email cannot be empty");
        }
        else if(!model.emailExists(Email)){
            view.displayMessage("Email not found");
        }
        else if (Password.equals("")){
            view.displayMessage("Password cannot be empty");
        }
        else {
            if (!model.userFound(Email, Password)){
                view.displayMessage("Wrong Password");
            }
            else{
                view.displayMessage("Should be Logged in successfully");
            }
        }
    }
}
