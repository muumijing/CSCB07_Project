package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class allorders extends AppCompatActivity {

    public RecyclerView RV;
    public String storeName;
    private String ownerId;


    // Arraylist for storing data
    public ArrayList<cardmodel> ModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        RV = findViewById(R.id.rv);
        storeName = getIntent().getStringExtra("storeName");
        ownerId = getIntent().getStringExtra("ownerId");
        System.out.println(storeName);


        // here we have created new array list and added data to it.
        ModelArrayList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Order");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot order_ids: snapshot.getChildren()){
                    String store_name = order_ids.child("storeName").getValue(String.class);
                    System.out.println(store_name);
                    if (store_name.equals(storeName)){
                        String Status = order_ids.child("status").getValue(String.class);
                        System.out.println(Status);
                        String orderId = order_ids.toString();
                        System.out.println(orderId);
                        ModelArrayList.add(new cardmodel(orderId, Status));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // we are initializing our adapter class and passing our arraylist to it.
        OrderAdapter orderAdapter = new OrderAdapter(this, ModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layout manager and adapter to our recycler view.
        RV.setLayoutManager(linearLayoutManager);
        RV.setAdapter(orderAdapter);
    }

    public void back(View view){
        Intent intent = new Intent(allorders.this, StoreOwnerPage.class);
        intent.putExtra("storeName", storeName);
        intent.putExtra("ownerId", ownerId);
        startActivity(intent);
    }



}

