package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07_project.adapter.ProductListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    public List<Product> storeProducts;


    //store the information that products from which store
    public String storeName;
    private Store store;
    public String ownerId;

    private Model model;

    ListView listView_products;
    ListView listView_price;
    ListView listView_quantity;
    private DatabaseReference storesRef;
    private DatabaseReference productsRef;
    private ListView products;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        storeName = getIntent().getStringExtra("storeName");
        ownerId = getIntent().getStringExtra("ownerId");
        model = Model.getInstance();

        products = (ListView) findViewById(R.id.products);

        getStore();
    }

    private void getStore() {
        model.getStoreByOwner(ownerId, (Store store) -> {
            this.store = store;

            ProductListAdapter adapter = new ProductListAdapter(this, R.layout.activity_product_detail, store.products_inventory);
            products.setAdapter(adapter);


        });
    }

    public void show_products(View view){

        //for every item in the order, get the string of the item
        //and the amount it needed

        listView_products = (ListView)findViewById(R.id.items);
        listView_quantity = (ListView)findViewById(R.id.amount);

        ArrayList<String> arrayList_items = new ArrayList<>();
        ArrayList<Integer> arrayList_amount = new ArrayList<>();
        ArrayList<Double> arrayList_price = new ArrayList<>();

        //ownerId = getIntent().getStringExtra("ownerId");
        //storeName = getIntent().getStringExtra("storeName");

        storesRef = FirebaseDatabase.getInstance().getReference("Stores");
        storesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String ownerID = ds.child("owner").getValue(String.class);
                    if (ownerID.equals(ownerId)) {

                        productsRef = storesRef.child(storeName).child("products");
                        productsRef.addValueEventListener(new ValueEventListener(){
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds1: snapshot.getChildren()){
                                    String Name = ds1.child("name").getValue(String.class);
                                    Integer Amount = ds1.child("inventory_quantity").getValue(Integer.class);
                                    Double price = ds1.child("price").getValue(Double.class);

                                    arrayList_items.add(Name);
                                    arrayList_amount.add(Amount);
                                    arrayList_price.add(price);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }


                        });


                    }

                }
            }

    //        Intent intent = new Intent(ProductListActivity.this, StoreOwnerPage.class);
     //                   intent.putExtra("ownerId", ownerId);
     //                   intent.putExtra("storeName", storeName);
     //       startActivity(intent);

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ArrayAdapter arrayAdapter_items = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList_items);
        ArrayAdapter arrayAdapter_amount = new ArrayAdapter(this, android.R.layout.simple_list_item_2,arrayList_amount);
        //ArrayAdapter arrayAdapter_price = new ArrayAdapter(this, android.R.layout.simple_list_item_3,arrayList_price);


        listView_products.setAdapter(arrayAdapter_items);
        listView_quantity.setAdapter(arrayAdapter_amount);
        //listView_price.setAdapter(arrayAdapter_price);


    }

    public void back(View view){
        Intent intent = new Intent(ProductListActivity.this, StoreOwnerPage.class);
        intent.putExtra("ownerId", ownerId);
        intent.putExtra("storeName", storeName);
        startActivity(intent);
    }

}
