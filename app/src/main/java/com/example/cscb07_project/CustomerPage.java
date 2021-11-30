package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerPage extends AppCompatActivity {
    private String username = "";
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        Intent intent = getIntent();
        username = intent.getStringExtra("message");
        TextView tv = (TextView)findViewById(R.id.welcome);
        tv.setText("Welcome" + username);

    }

    public void customerLogout(View view){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String name = ds.child("username").getValue(String.class);
                    if(name.equals(username)){
                        write(username, "login", "false");
                        startActivity (new Intent(CustomerPage.this, MainActivity.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void write(String username, String field, String data){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Users");
        ref.child(username).child(field).setValue(data);
    }
}