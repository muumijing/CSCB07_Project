package com.example.cscb07_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cscb07_project.R;
import com.example.cscb07_project.model.productDetail;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.Viewholder> {
    public Context context;
    public ArrayList<productDetail> ModelArrayList;
    // Constructor
    public ProductListAdapter(Context context, ArrayList<productDetail> ModelArrayList) {
        this.context = context;
        this.ModelArrayList = ModelArrayList;
    }
    @NonNull
    @Override
    public ProductListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_detail, parent, false);
        return new ProductListAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        productDetail model = ModelArrayList.get(position);
        holder.productNameTV.setText(model.getName());
        holder.productPriceTV.setText(model.getPrice().toString());
        holder.productQuantityTV.setText(model.getQuantity());
    }

    @Override
    public int getItemCount() {
        return ModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView productNameTV, productPriceTV,productQuantityTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            productNameTV = itemView.findViewById(R.id.productName);
            productPriceTV = itemView.findViewById(R.id.productPrice);
            productQuantityTV = itemView.findViewById(R.id.productQuantity);
        }
    }


}
