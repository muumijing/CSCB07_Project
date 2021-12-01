package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updateProductActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText addProductName, addPrice, addQuantity;
    private Button btnSave;

    private int i;
    private Store store;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        store = (Store) getIntent().getSerializableExtra("store");

        userID = getIntent().getStringExtra("usrID");

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



    }

    @Override
    public void onClick(View v) {

    }
}
