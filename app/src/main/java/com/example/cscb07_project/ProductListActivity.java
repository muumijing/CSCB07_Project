package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07_project.adapter.ProductListAdapter;
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
    public ListView lv;

    // Arraylist for storing data
    public ArrayList<productDetail> ModelArrayList;

    //store the information that products from which store
    public String storeName;
    private Store store;
    public String ownerId;

    private Model model;


    private DatabaseReference storesRef;
    private DatabaseReference productsRef;
    

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

        lv = findViewById(R.id.productRv);

        // here we have created new array list and added data to it.
//        ModelArrayList = new ArrayList<productDetail>();
//        storesRef = FirebaseDatabase.getInstance().getReference("Stores");
//        storesRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds: snapshot.getChildren()){
//                    String ownerID = ds.child("owner").getValue(String.class);
//                    if (ownerID.equals(ownerId)) {

//                        productsRef = storesRef.child(storeName).child("products");
//                        productsRef.addValueEventListener(new ValueEventListener(){
 //                           @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                for(DataSnapshot ds1: snapshot.getChildren()){
//                                    String name = ds1.child("name").getValue(String.class);
//                                    Integer quantity = ds1.child("inventory_quantity").getValue(Integer.class);
//                                    Double price = ds1.child("price").getValue(Double.class);
//                                    ModelArrayList.add(new productDetail(name,price,quantity));

//                                }
 //                           }

 //                           @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                            }


//                        });
//
//                    }

//                }
 //           }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
        // we are initializing our adapter class and passing our arraylist to it.
//        ProductListAdapter productListAdapter = new ProductListAdapter(this, ModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layout manager and adapter to our recycler view.
//        RV.setLayoutManager(linearLayoutManager);
//        RV.setAdapter(productListAdapter);


        getProduct();

    }

    public void getProduct(){
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
                                    productDetail p = new productDetail(name,price,quantity);
                                    if(!ModelArrayList.contains(p)){
                                        ModelArrayList.add(p);
                                    }

                                    for(productDetail p1:ModelArrayList){
                                        System.out.println(p1.name);
                                    }
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
        ProductListAdapter productListAdapter = new ProductListAdapter
                (this, R.layout.activity_product_detail,ModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layout manager and adapter to our recycler view.
        //lv.setLayoutManager(linearLayoutManager);
        lv.setAdapter(productListAdapter);
        System.out.println("b");

    }



    public void back(View view){
        Intent intent = new Intent(ProductListActivity.this, StoreOwnerPage.class);
        intent.putExtra("ownerId", ownerId);
        intent.putExtra("storeName", storeName);
        startActivity(intent);
    }

}
