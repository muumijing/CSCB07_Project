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
    private Button btnSave;

    private int i;
    private Store store;
    private String username;

    private FirebaseDatabase db;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        store = (Store) getIntent().getSerializableExtra("store");

        username = getIntent().getStringExtra("username");

        addProductName = (EditText) findViewById(R.id.addProductName);
        addPrice = (EditText) findViewById(R.id.addPrice);
        addQuantity = (EditText) findViewById(R.id.addQuantity);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);


    }

    private void fillEdTxt() {
        //
        if (i != -1) {
            Product product = store.products_inventory.get(i);
            addProductName.setText(product.name);
            addPrice.setText(((Double) product.price).toString());
            addQuantity.setText(((Integer) product.inventory_quantity).toString());
        }

    }
    private void save() {
        Product product = new Product(
                addProductName.getText().toString().trim(),
                Double.parseDouble(addPrice.getText().toString()),
                Integer.parseInt(addQuantity.getText().toString())
        );

        // update the inventory of a product
        if (i != -1) {
            store.products_inventory.set(i, product);
        }
        // add new product to the store inventory
        else {
            store.products_inventory.add(product);
        }

        // update fire base

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Stores");
        //write(addProductName.getText().toString().trim(),"products", "true");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean registered = false;
                for(DataSnapshot ds: snapshot.getChildren()){
                    String owner = ds.child("email").getValue(String.class);
                    String products = ds.child("username").getValue(String.class);

                    ref.child(products).setValue(addProductName);
                    Toast.makeText(updateProductActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(updateProductActivity.this, StoreOwnerPage.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    //public void write(String product, String field, String data){
       // db = FirebaseDatabase.getInstance();
        //ref = db.getReference("Stores");
        //ref.child(product).child(field).setValue(data);
   // }

    @Override
    public void onClick(View v) {

    }
}
