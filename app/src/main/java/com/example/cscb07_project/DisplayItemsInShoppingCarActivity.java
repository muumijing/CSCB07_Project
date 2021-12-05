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

    public ArrayList<Product> products = new ArrayList<Product>();
    public ArrayList<Order> orders = new ArrayList<Order>();
    private Order order;
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

        order = new Order(storeName, customerId);
        order.addProducts(new Product("Apple", 2.3, 3));
        order.addProducts(new Product("Pear", 1.8, 1));
        order.addProducts(new Product("Banana", 3.0, 4));

//        products.add(new Product("Apple", 2.3, 3));
//        products.add(new Product("Pear", 1.8, 1));
//        products.add(new Product("Banana", 3.0, 4));

        products = order.orderProducts;
        for (Product p : products){
            totalPrice += p.getPrice();
        }

        tvTotalPrice = (TextView) findViewById(R.id.orderTotalPrice);
        String price = ((Double) totalPrice).toString();
        tvTotalPrice.setText(price);

        ProductsAdapter productsAdapter = new ProductsAdapter(DisplayItemsInShoppingCarActivity.this, R.layout.shopping_car_layout, products);
        lvOrderItems.setAdapter(productsAdapter);


        checkoutBtn = (Button) findViewById(R.id.checkoutButton);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toConfirmOrderCheckout();
            }
        });

        returnToHome = (ImageButton) findViewById(R.id.homePageBtn);
        returnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomePage();
            }
        });

//        ListView listView = (ListView) findViewById(R.id.listViewItems);
//
//        products.add(new Product("Apple", 2.3, 3));
//        products.add(new Product("Pear", 1.8, 1));
//        products.add(new Product("Banana", 3.0, 4));
//
//        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.shopping_car, products);
//
//        listView.setAdapter(itemAdapter);
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(DisplayItemsInShoppingCarActivity.this, PopupActivity.class);
//                startActivity(intent);
//
//            }
//        });


    }

    public void toConfirmOrderCheckout (){
        Intent intent = new Intent(DisplayItemsInShoppingCarActivity.this, PopupActivity.class);
        intent.putExtra("storeName", storeName);
        intent.putExtra("customerId", customerId);
        intent.putExtra("customerName", customerName);
        intent.putExtra("order", order.orderProducts);
        startActivity(intent);
    }

    public void backToHomePage (){
        Intent intent = new Intent(DisplayItemsInShoppingCarActivity.this, CustomerPage.class);
        intent.putExtra("customerName", customerName);
        startActivity(intent);

    }

}