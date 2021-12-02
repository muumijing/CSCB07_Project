package com.example.cscb07_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerPage extends AppCompatActivity {
    private String username = "";
    private String customerId = "";
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        customerId = intent.getStringExtra("customerId");
        TextView tv = (TextView)findViewById(R.id.welcome);
        tv.setText("Welcome" + username);

    }

    public void customerLogout(View view){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Customers");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String customerId1 = "customer" + ds.child("phoneNum").getValue(String.class);
                    if(customerId1.equals(customerId)){
                        ref.child(customerId).child("login").setValue("false");
                        // write(username, "login", "false");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        startActivity (new Intent(CustomerPage.this, MainActivity.class));

    }
}