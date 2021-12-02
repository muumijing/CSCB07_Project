package com.example.cscb07_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Product> {

    private int resource;

    private static class ViewHolder {
        TextView productName;
        TextView productQuantity;
    }

    public ItemAdapter(Context context, int resource, List<Product> products) {
        super(context, resource);
        this.resource = resource;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        Product item = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.productName = (TextView) convertView.findViewById(R.id.textViewItemName);
            viewHolder.productQuantity = (TextView) convertView.findViewById(R.id.textViewQuantity);

            convertView.setTag(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.productName.setText(item.name);
        viewHolder.productQuantity.setText(item.inventory_quantity);

        return convertView;
    }

}
