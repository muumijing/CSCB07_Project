package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MVP_CustomerLogin_Activity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;

    private Button login;



    private Model model;

    private FirebaseDatabase db;
    private DatabaseReference ref;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_customerlogin);



        model = Model.getInstance();
        presenter = new Presenter(model, this);

    }




    public void logIn(View view) {
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();



        if (email.isEmpty()) {
            inputEmail.setError("Please enter your email");
            inputEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("Email is not valid");
            inputEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            inputPassword.setError("Please enter your password");
            inputPassword.requestFocus();
            return;
        }

        if (password.length() < 12) {
            inputPassword.setError("Password must contain at least 12 characters");
            inputPassword.requestFocus();
            return;
        }
        presenter.customerLogin(email,password);



    }
    public void linkToCustomerPage(String ownerId){
        Intent intent = new Intent(this,
                CustomerPage.class);

        startActivity(intent);
    }

    public void loginFailure(){

        Toast.makeText(this, "fail to login", Toast.LENGTH_LONG).show();

    }
}
