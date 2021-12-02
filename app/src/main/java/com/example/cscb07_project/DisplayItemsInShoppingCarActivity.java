package com.example.cscb07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayItemsInShoppingCarActivity extends AppCompatActivity {

    public List<Product> products = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items_in_shopping_car);

        ListView listView = (ListView) findViewById(R.id.listViewItems);

        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.shopping_car, products);

        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DisplayItemsInShoppingCarActivity.this, PopupActivity.class);
                startActivity(intent);

            }
        });


    }
}