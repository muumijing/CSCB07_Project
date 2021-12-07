package com.example.cscb07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayItemsInShoppingCarActivity extends AppCompatActivity {

    public ArrayList<CustomProductView> products = new ArrayList<CustomProductView>();
    public ArrayList<OrderInfo> orders = new ArrayList<OrderInfo>();
    private OrderInfo order;
    private Button checkoutBtn;
    private ImageButton returnToHome;
    private String storeName;
    private String customerName;
    private String customerId;

    private double totalPrice = 0;
    private TextView tvTotalPrice;

    private ListView lvOrderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items_in_shopping_car);

        lvOrderItems = (ListView) findViewById(R.id.listViewItems);
//        orders = getIntent().getParcelableExtra("orderItem");

        Intent intent = getIntent();
        storeName = intent.getStringExtra("storeName");
        customerId = intent.getStringExtra("customerId");
        customerName = intent.getStringExtra("customerName");
        products = (ArrayList<CustomProductView>) intent.getSerializableExtra("orderList");

//        order = new OrderInfo(storeName, customerId);

        for (CustomProductView p : products){
            totalPrice += p.getProductPrice();
        }

        tvTotalPrice = (TextView) findViewById(R.id.orderTotalPrice);
        String price = ((Double) totalPrice).toString();
        tvTotalPrice.setText(price);

        ProductsAdapter productsAdapter = new ProductsAdapter(DisplayItemsInShoppingCarActivity.this, R.layout.shopping_car_layout, products);
        lvOrderItems.setAdapter(productsAdapter);


//        checkoutBtn = (Button) findViewById(R.id.checkoutButton);
//        checkoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toConfirmOrderCheckout();
//            }
//        });

        returnToHome = (ImageButton) findViewById(R.id.homePageBtn);
        returnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomePage();
            }
        });
    }

    public void toConfirmOrderCheckout (){
        Intent intent = new Intent(DisplayItemsInShoppingCarActivity.this, PopupActivity.class);
        intent.putExtra("storeName", storeName);
        intent.putExtra("customerId", customerId);
        intent.putExtra("customerName", customerName);
        startActivity(intent);
    }

    public void backToHomePage (){
        Intent intent = new Intent(DisplayItemsInShoppingCarActivity.this, CustomerPage.class);
        intent.putExtra("customerId", customerId);
        intent.putExtra("customerName", customerName);
        startActivity(intent);

    }

}