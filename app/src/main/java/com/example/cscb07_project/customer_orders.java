package com.example.cscb07_project;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customer_orders extends AppCompatActivity {

    ListView listView_orderid;
    ListView listView_status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_checkorders);
    }

    public void show_cus_order (View view){
        listView_orderid = (ListView)findViewById(R.id.orderid);
        listView_status = (ListView) findViewById(R.id.status);

        ArrayList<String> arrayList_orderid = new ArrayList<>();
        ArrayList<String> arrayList_status = new ArrayList<>();

        Intent intent = getIntent();
        String customerid = intent.getStringExtra("customerId");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("stores");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot storenames: snapshot.getChildren()){
                    DatabaseReference orders = reference.child(storenames.toString()).child("orders");
                    orders.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot orderids: snapshot.getChildren()){
                                if(orderids.child("customerid").getValue(String.class).equals(customerid)){
                                    arrayList_orderid.add(orderids.toString());
                                    arrayList_status.add(orderids.child("status").getValue(String.class));
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ArrayAdapter arrayAdapter_orderid = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList_orderid);
        ArrayAdapter arrayAdapter_status = new ArrayAdapter(this, android.R.layout.simple_list_item_2,arrayList_orderid);

        listView_orderid.setAdapter(arrayAdapter_orderid);
        listView_status.setAdapter(arrayAdapter_status);
        
    }
}
