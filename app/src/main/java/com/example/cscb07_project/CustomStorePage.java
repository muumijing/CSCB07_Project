package com.example.cscb07_project;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cscb07_project.database.DataManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CustomStorePage extends AppCompatActivity {
    List<CustomStoreView> stores;
    FirebaseDatabase db;
    DatabaseReference StoreData;
    //DatabaseReference StoreOwner;
    private String username = "";
    private String customerId = "";
    private Button ShoppingCarBtn;
    private Button returnToHomeBtn;
    private ListView listView;


    public void requestDataAsync(ValueEventListener listener) {
        //int amount = 0;
        db = FirebaseDatabase.getInstance();
        StoreData = db.getReference("Stores");
        StoreData.addValueEventListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_store_page);
        Intent intent = getIntent();
        username = intent.getStringExtra("customerName");
        customerId = intent.getStringExtra("customerId");
        ShoppingCarBtn = (Button) findViewById(R.id.ShoppingCarButton);
        ShoppingCarBtn.setOnClickListener(this::viewShoppingCar);

        returnToHomeBtn = (Button) findViewById(R.id.storePageReturnToHomeBtn);
        returnToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomStorePage.this, CustomStorePage.class);
                intent.putExtra("customerName", username);
                intent.putExtra("customerId", customerId);
                startActivity(intent);
            }
        });

        initView();
    }

    public void viewShoppingCar(View view) {


        Intent intent = new Intent(CustomStorePage.this, ShoppingCar.class);
        intent.putExtra("customerName", username);
        intent.putExtra("customerId", customerId);
//       intent.putExtra("items", orderProductList);
        startActivity(intent);
    }

    private void initView() {
        listView = findViewById(R.id.listViewStore);
        stores = new ArrayList<>();
        requestDataAsync(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String StoreName = ds.child("storeName").getValue(String.class);
                    String StoreOwner = ds.child("owner").getValue(String.class);
                    stores.add( new CustomStoreView(StoreName, StoreOwner));
                }
                if (stores.isEmpty()) {
                    Toast.makeText(CustomStorePage.this, "No data~", Toast.LENGTH_SHORT).show();
                    return;
                }
                flushList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CustomStorePage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void flushList() {
        CustomStoreAdapter CustomStoreAdapter = new CustomStoreAdapter(CustomStorePage.this, stores);
        listView.setAdapter(CustomStoreAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CustomStoreView store = stores.get(position);

            Intent intent = new Intent(CustomStorePage.this, CustomProductPage.class);
            Bundle bundle = new Bundle();
            intent.putExtra("customerName", username);
            intent.putExtra("customerId", customerId);
            bundle.putSerializable(CustomProductPage.BUNDLE_ARG_STORE_DATA, store);
            intent.putExtras(bundle);
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }
}

