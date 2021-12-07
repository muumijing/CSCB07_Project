package com.example.cscb07_project;

public interface Contract {
    public interface MVPOwnerModel{
        public boolean emailExists(String email);
        public boolean userFound(String email, String password);
    }

    public interface MVPOwnerView{
        public String getEmail();
        public String getPassword();
        public void displayMessage(String message);
    }

    public interface MVPOwnerPresenter{
        public void checkLoginStatus();
    }
}
