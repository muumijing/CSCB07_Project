package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapter.categoryAdapter;

public class StoreOwnerPage extends AppCompatActivity implements View.OnClickListener{

    private String userID;

    private Store store;

    RecyclerView categoryRecyclerView;

    adapter.categoryAdapter categoryAdapter;
    List<Category> categoryList;

    TextView allCategory;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);

        userID = getIntent().getStringExtra("userID");

    }

    public void ownerLogout(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    //private void addItem() {
    //Intent intent = new Intent(this, updateProductActivity.class);
    // intent.putExtra("store", store);
    //intent.putExtra("store", store);
    //startActivity(intent);
    //}
    @Override
    public void onClick(View view) {

    }
}

