package com.example.cscb07_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cscb07_project.Product;
import com.example.cscb07_project.R;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private int resource;
    private Context context;

    //constructor
    public ProductListAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    private class ViewHolder {
        TextView productName;
        TextView productPrice;
        TextView productQuantity;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String itemName = getItem(position).name;
        String price = ((Double) getItem(position).price).toString();
        String quantity = ((Integer) getItem(position).inventory_quantity).toString();


        ViewHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.productName = (TextView) convertView.findViewById(R.id.productName);
            holder.productPrice = (TextView) convertView.findViewById(R.id.productPrice);
            holder.productQuantity = (TextView) convertView.findViewById(R.id.productQuantity);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.productName.setText(itemName);
        holder.productPrice.setText(price);
        holder.productQuantity.setText(quantity);

        return convertView;
    }
}
