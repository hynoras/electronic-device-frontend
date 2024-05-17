package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context mContext;
    private List<Product> productList;

    public ProductAdapter(@NonNull Context context, List<Product> list) {
        super(context, 0, list);
        mContext = context;
        productList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent,false);

        Product currentProduct = productList.get(position);

        TextView name = listItem.findViewById(R.id.productName);
        name.setText(currentProduct.getProdName());

        TextView price = listItem.findViewById(R.id.productPrice);
        price.setText(String.valueOf(currentProduct.getProdPrice()));

        ImageView imageView = listItem.findViewById(R.id.productImage);
        Picasso.get().load(currentProduct.getProdImg()).into(imageView);

        return listItem;
    }
}

