package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07_project.model.productDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    public List<Product> storeProducts;
    public RecyclerView RV;

    // Arraylist for storing data
    public ArrayList<productDetail> ModelArrayList;

    //store the information that products from which store
    public String storeName;
    private Store store;
    public String ownerId;

    private Model model;

    ListView lv_products;
    ListView lv_price;
    ListView lv_quantity;
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

        //lv_products = (ListView) findViewById(R.id.productList);
        //lv_price = (ListView) findViewById(R.id.priceList);
        //lv_quantity = (ListView) findViewById(R.id.quantityList);

        RV = findViewById(R.id.productRv);

        // here we have created new array list and added data to it.
        ModelArrayList = new ArrayList<productDetail>();
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
                                    String name = ds1.child("name").getValue(String.class);
                                    Integer quantity = ds1.child("inventory_quantity").getValue(Integer.class);
                                    Double price = ds1.child("price").getValue(Double.class);

                                    ModelArrayList.add(new productDetail(name,price,quantity));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }


                        });

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
        getStore();

    }

    private void getStore() {
        model.getStoreByOwner(ownerId, (Store store) -> {
            this.store = store;

            //ProductListAdapter adapter = new ProductListAdapter(this, R.layout.activity_product_detail, store.products_inventory);
            //products.setAdapter(adapter);


        });
    }

    public void show_products(View view){

        //for every product in the shop, get the string of the item
        //,the quantity and the price



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
                                    String name = ds1.child("name").getValue(String.class);
                                    Integer amount = ds1.child("inventory_quantity").getValue(Integer.class);
                                    Double price = ds1.child("price").getValue(Double.class);

                                    arrayList_items.add(name);
                                    arrayList_amount.add(amount);
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ArrayAdapter arrayAdapter_items = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList_items);
        ArrayAdapter arrayAdapter_amount = new ArrayAdapter(this, android.R.layout.simple_list_item_2,arrayList_amount);
        //ArrayAdapter arrayAdapter_price = new ArrayAdapter(this, android.R.layout.simple_list_item_3,arrayList_price);


        lv_products.setAdapter(arrayAdapter_items);
        lv_quantity.setAdapter(arrayAdapter_amount);
        //listView_price.setAdapter(arrayAdapter_price);


    }

    public void back(View view){
        Intent intent = new Intent(ProductListActivity.this, StoreOwnerPage.class);
        intent.putExtra("ownerId", ownerId);
        intent.putExtra("storeName", storeName);
        startActivity(intent);
    }

}
