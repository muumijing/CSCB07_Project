package com.example.cscb07_project;

public class MVPCusPresenter implements Contract.MVPCusPresenter{
    public Contract.MVPCusModel model;
    public Contract.MVPCusView view;

    public MVPCusPresenter(MVPCusModel model, MVPCusLogin view){
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
