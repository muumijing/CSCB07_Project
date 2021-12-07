package com.example.cscb07_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cscb07_project.database.DataManager;
import com.example.cscb07_project.database.ProductInfo;
import com.example.cscb07_project.database.ShoppingRecord;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCar extends AppCompatActivity {
    public ArrayList<CustomProductView> products;
    private Button viewShoppingCarBtn;
    private Button returnHomeBtn;
//    private OrderInfo order;
    private String customerName;
    private String customerId;

    //TODO 加入购物车的信息
    //    public static final String BUNDLE_ARG_ORDER_INFO = "ORDER_INFO";
    private NewCustomOrderInfoAdapter newCustomOrderInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        if (intent != null && intent.getExtras() != null) {
//            orderInfo = (OrderInfo) intent.getExtras().getSerializable(BUNDLE_ARG_ORDER_INFO);
//        }

        Intent intent = getIntent();
        customerName = intent.getStringExtra("customerName");
        customerId = intent.getStringExtra("customerId");

        setContentView(R.layout.shopping_car);
        viewShoppingCarBtn = (Button) findViewById(R.id.checkoutButton);
        viewShoppingCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetailShoppingCar();
            }
        });

        returnHomeBtn = (Button)findViewById(R.id.SCarReturnToHomeBtn);
        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCar.this, CustomerPage.class);
                intent.putExtra("customerName", customerName);
                intent.putExtra("customerId", customerId);
                startActivity(intent);
            }
        });


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvOrderInfo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        newCustomOrderInfoAdapter = new NewCustomOrderInfoAdapter(ShoppingCar.this, getData(), new NewCustomOrderInfoAdapter.OnWidgetDealListener() {
            @Override
            public void deleteShoppingCar(CustomProductView customProductView) {
                //删除数量1

                ShoppingRecord shoppingRecord = new ShoppingRecord();
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductName(customProductView.getProductName());
                productInfo.setStoreName(customProductView.getStoreName());
                productInfo.setProductPrice(customProductView.getProductPrice());
                shoppingRecord.setAccount(Global.account);
                shoppingRecord.setProductInfo(productInfo);

                int amount = DataManager.getInstance().getShoppingRecord(shoppingRecord).get(0).getProductAmount();
                if (amount == 1) {
                    //最少一条,直接删除数据 刷新数据
                    DataManager.getInstance().deleteShoppingRecord(shoppingRecord);
                } else {
                    //修改数据 刷新数据
                    amount = amount-1;
                    shoppingRecord.setProductAmount(amount);
                    DataManager.getInstance().alterShoppingRecord(shoppingRecord);
                }
                newCustomOrderInfoAdapter.update(getData());
            }
        });
        recyclerView.setAdapter(newCustomOrderInfoAdapter);

//        products.add(new Product("Apple", 2.3, 3));
//        products.add(new Product("Pear", 1.8, 1));
//        products.add(new Product("Banana", 3.0, 4));


    }

    private ArrayList<CustomProductView> getData(){
        ArrayList<CustomProductView> list = new ArrayList<>();
        List<ShoppingRecord> data = DataManager.getInstance().getAllShoppingRecord(Global.account);

        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                CustomProductView customProductView = new CustomProductView(
                        data.get(i).getProductInfo().getStoreName(),
                        data.get(i).getProductInfo().getProductName(),
                        data.get(i).getProductInfo().getProductPrice(),
                        data.get(i).getProductAmount());
                list.add(customProductView);
            }
        }
        return list;
    }

    public void viewDetailShoppingCar (){

        Intent intent = new Intent(ShoppingCar.this, PopupActivity.class);
        OrderInfo orderInfo = new OrderInfo(customerId, "pending",customerName);
        orderInfo.setProductList(getData());
        intent.putExtra("order", orderInfo);
        intent.putExtra("customerName", customerName);
        intent.putExtra("customerId", customerId);
        startActivity(intent);
        DataManager.getInstance().deleteAllShoppingRecord(Global.account);
        newCustomOrderInfoAdapter.update();
    }


}