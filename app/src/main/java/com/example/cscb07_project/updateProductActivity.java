package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateProductActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText addProductName, addPrice, addQuantity;
    private Button save;


    private Store store;
    private String username;
    private String storeName;
    private String ownerId;


    private Model model;
    private DatabaseReference storesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);
        model = Model.getInstance();

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        //getStore();


    }

    private void getStore() {
        model.getStoreByOwner(username, (Store store) -> {

            this.store = store;
            //ItemListAdapter adapter = new ItemListAdapter(this, R.layout.item_list_item, store.inventory);
            //lvItems.setAdapter(adapter);

        });
    }
    public void updateItem() {

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        ownerId = intent.getStringExtra("ownerId");
        storeName = intent.getStringExtra("storeName");
        System.out.println(storeName);
        addProductName = (EditText) findViewById(R.id.addProductName);
        addPrice = (EditText) findViewById(R.id.addPrice);
        addQuantity = (EditText) findViewById(R.id.addQuantity);
        storesRef = FirebaseDatabase.getInstance().getReference("Stores");


        storesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    String ownerID = ds.child("owner").getValue(String.class);
                    System.out.println(ownerID);
                    System.out.println(ownerId);
                    //String username1 = ds.child("username").getValue(String.class);
                    Product product = new Product(
                            addProductName.getText().toString().trim(),
                            Double.parseDouble(addPrice.getText().toString()),
                            Integer.parseInt(addQuantity.getText().toString())
                    );
                    if (ownerID.equals(ownerId)) {
                        storesRef.child(storeName).child("products").child("name").setValue(addProductName.getText().toString().trim());
                        storesRef.child(storeName).child("products").child("price").setValue(addPrice.getText().toString());
                        storesRef.child(storeName).child("products").child("quantity").setValue(addQuantity.getText().toString());
                        Toast.makeText(updateProductActivity.this, "product has been saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(updateProductActivity.this, StoreOwnerPage.class));
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save) {
            updateItem();
        }
    }


//    private void fillEdTxt() {
        //
//        if (i != -1) {
//            Product product = store.products_inventory.get(i);
//            addProductName.setText(product.name);
//            addPrice.setText(((Double) product.price).toString());
//            addQuantity.setText(((Integer) product.inventory_quantity).toString());
 //       }

 //   }





}
