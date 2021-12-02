package com.example.cscb07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ShoppingCar extends AppCompatActivity {
    public List<Product> products;
    private Button viewShoppingCarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_car);
        viewShoppingCarBtn = (Button) findViewById(R.id.checkoutButton);
        viewShoppingCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetailShoppingCar();
            }
        });

    }

    public void viewDetailShoppingCar (){
        //Intent intent = new Intent(ShoppingCar.this, PlaceOrderActivity.class);
        Intent intent = new Intent(ShoppingCar.this, PopupActivity.class);
        intent.putExtra("item", "hello");
        startActivity(intent);
    }

    public List<Product> getOrderList (){
        return products;
    }
}