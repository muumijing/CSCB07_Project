package com.example.cscb07_project;

public class Presenter {
    private Model model;
    private MVP_OwnerLogin_Activity ownerView;
    private CustomerLogin customerView;

    public Presenter(Model model, MVP_OwnerLogin_Activity view){
        this.model = model;
        this.ownerView = view;
    }

    public Presenter(Model model, CustomerLogin view){
        this.model = model;
        this.customerView = view;
    }

    public void ownerLogin(String email, String password){
        model.authenticate1(email,password, (Owner owner) ->{
            if(owner == null) ownerView.loginFailure();
            ownerView.linkToOwnerPage(owner.ownerId);
        });
    }

    public void customerLogin(String email, String password){
        model.authenticate2(email,password, (Customer customer) ->{
            if(customer == null) customerView.loginFailure();
            customerView.linkToCustomerPage(customer.customerId);
        });
    }
}
