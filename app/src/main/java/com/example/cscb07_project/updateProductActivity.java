package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateProductActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText addProductName, addPrice, addQuantity;
    private Button save;

    private int i;
    private Store store;
    private String username;

    private FirebaseDatabase db;
    private DatabaseReference ref;
    private Model model;
    private DatabaseReference storesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);
        model = Model.getInstance();


        getStore();
        storesRef = FirebaseDatabase.getInstance().getReference("Stores");

    }

    private void getStore() {
        model.getStoreByOwner(username, (Store store) -> {

            this.store = store;
            //ItemListAdapter adapter = new ItemListAdapter(this, R.layout.item_list_item, store.inventory);
            //lvItems.setAdapter(adapter);

        });
    }
    public void updateItem(){
        Intent intent = getIntent();
        username = intent.getStringExtra("message");

        addProductName = (EditText) findViewById(R.id.addProductName);
        addPrice = (EditText) findViewById(R.id.addPrice);
        addQuantity = (EditText) findViewById(R.id.addQuantity);



        save = (Button) findViewById(R.id.save);
        save.setOnClickListener((View.OnClickListener) this);

        //ref.addValueEventListener(new ValueEventListener() {
            //@Override
            //public void onDataChange(@NonNull DataSnapshot snapshot) {

                //for (DataSnapshot ds : snapshot.getChildren()) {
                    //String owner = ds.child("owner").getValue(String.class);
                    //String username1 = ds.child("username").getValue(String.class);
                    Product product = new Product(
                            addProductName.getText().toString().trim(),
                            Double.parseDouble(addPrice.getText().toString()),
                            Integer.parseInt(addQuantity.getText().toString())
                    );
                    // update the inventory of a product
                    //if (i != -1) {
                        //store.products_inventory.set(i, product);
                     //}
                    // add new product to the store inventory
                    //else {
                        store.products_inventory.add(product);
                    //}
                    //storesRef.child(String.valueOf(store.products_inventory)).child("name").setValue(addProductName.getText().toString().trim());

                    // update fire base
                    model.postStore(store, (Boolean posted) -> {
                        if (!posted) {
                            Toast.makeText(updateProductActivity.this, "Products can not be saved", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(updateProductActivity.this, "product has been saved", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(updateProductActivity.this, StoreOwnerPage.class);
                        intent2.putExtra("message", store.owner);
                        startActivity(intent2);

                    });






            //@Override
            //public void onCancelled(@NonNull DatabaseError error) {
           // }
        //});
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



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                updateItem();
                break;
        }
    }

}
