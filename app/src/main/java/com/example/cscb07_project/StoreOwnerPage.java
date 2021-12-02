package com.example.cscb07_project;

import static com.example.cscb07_project.R.drawable.ic_home_fish;
import static com.example.cscb07_project.R.drawable.ic_home_fruits;
import static com.example.cscb07_project.R.drawable.ic_home_meats;
import static com.example.cscb07_project.R.drawable.ic_home_veggies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07_project.adapter.CategoryAdapter;
import com.example.cscb07_project.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerPage extends AppCompatActivity{

    private String username = "";
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private Button button;
    private Store store;

    RecyclerView categoryRecyclerView;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        Intent intent = getIntent();

        username = intent.getStringExtra("message");
        //TextView tv = (TextView)findViewById(R.id.welcome);
        //tv.setText("Welcome Owner" + username);

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1,ic_home_fruits));
        categoryList.add(new Category(2,ic_home_fish));
        categoryList.add(new Category(3,ic_home_meats));
        categoryList.add(new Category(4,ic_home_veggies));

        setCategoryRecycler(categoryList);
        //button = (Button) findViewById(R.id.AddItem);
        //button.setOnClickListener(new View.OnClickListener(){

            //@Override
            //public void onClick(View v) {
                //addItem();
            //}
        //});


    }

    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    public void ownerLogout(View view){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Owners");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String name = ds.child("username").getValue(String.class);
                    if(name.equals(username)){
                        write(username, "login", "false");
                        startActivity (new Intent(StoreOwnerPage.this, MainActivity.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void write(String username, String field, String data){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Stores");
        ref.child(username).child(field).setValue(data);
    }
    public void addItem(View view) {
        Intent intent = new Intent(this, updateProductActivity.class);
        intent.putExtra("message", username);
        startActivity(intent);
    }



}

