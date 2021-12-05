package com.example.cscb07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShoppingCar extends AppCompatActivity {
//    public List<Product> products;
    private Button viewShoppingCarBtn;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_car_layout);
        viewShoppingCarBtn = (Button) findViewById(R.id.checkoutButton);
        viewShoppingCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetailShoppingCar();
            }
        });

//        products.add(new Product("Apple", 2.3, 3));
//        products.add(new Product("Pear", 1.8, 1));
//        products.add(new Product("Banana", 3.0, 4));


    }

    public void viewDetailShoppingCar (){
        //Intent intent = new Intent(ShoppingCar.this, PlaceOrderActivity.class);
//        order.orderProducts.add(new Product("Apple", 2.3, 3));
//        order.orderProducts.add(new Product("Banana", 1.8, 1));
//        order.orderProducts.add(new Product("Pear", 3.5, 4));

        Intent intent = new Intent(ShoppingCar.this, PopupActivity.class);
//        intent.putExtra("order", (Parcelable) order);
        startActivity(intent);
    }

//    public List<Product> getOrderList (){
//        return products;
//    }
}