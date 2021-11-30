package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;



public class StoreOwnerPage extends AppCompatActivity implements View.OnClickListener{

    private String username = "";
    private FirebaseDatabase db;
    private DatabaseReference ref;

    //private Store store;

    RecyclerView categoryRecyclerView;


    //List<Category> categoryList;

    //TextView allCategory;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        Intent intent = getIntent();
        username = intent.getStringExtra("message");
        TextView tv = (TextView)findViewById(R.id.welcome);
        tv.setText("Welcome Owner" + username);


    }

    public void ownerLogout(View view){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Owners");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String name = ds.child("username").getValue(String.class);
                    if(name.equals(username)){
                        write(username, "login", "false");
                        startActivity (new Intent(StoreOwnerPage.this, MainActivity.class));
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
        ref = db.getReference("Owners");
        ref.child(username).child(field).setValue(data);
    }
    //private void addItem() {
    //Intent intent = new Intent(this, updateProductActivity.class);
    // intent.putExtra("store", store);
    //intent.putExtra("store", store);
    //startActivity(intent);
    //}
    @Override
    public void onClick(View view) {

    }
}

