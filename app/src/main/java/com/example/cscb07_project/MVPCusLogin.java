package com.example.cscb07_project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MVPCusLogin extends AppCompatActivity implements Contract.MVPCusView {
    public Contract.MVPCusPresenter presenter;

    public void displayMessage (String message){
        TextView textView = findViewById(R.id.LoginStatus);
        textView.setText(message);
    }

    public String getEmail(){
        EditText editText = findViewById(R.id.email);
        return editText.getText().toString();
    }

    public String getPassword(){
        EditText editText = findViewById(R.id.password);
        return editText.getText().toString();
    }

    public void handleClick(View view){presenter.checkLoginStatus();}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpcuslogin);

        presenter = new MVPCusPresenter(new MVPCusModel(), this);
    }
}
