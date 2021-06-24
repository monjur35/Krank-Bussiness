package com.example.krankbusiness.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankbusiness.R;
import com.example.krankbusiness.models.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<Product>productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(context).inflate(R.layout.product_row,parent,false);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ProductViewHolder holder, int position) {

        Product product=productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.price.setText(product.getProductPrice()+" BDT");
        holder.inStock.setText(product.getTotalInStock()+" Pcs");
        if (product.getTotalInStock()==0){
            holder.card.setBackgroundColor(Color.parseColor("#FF8585"));
        }
        String productId=product.getProductId();
        Bundle bundle=new Bundle();
        bundle.putString("productId",productId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addProducrFragment_to_productDetailsFragment,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName,price,inStock;
        LinearLayout card;

        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productName=itemView.findViewById(R.id.productName);
            price=itemView.findViewById(R.id.price);
            inStock=itemView.findViewById(R.id.inStock);
            card=itemView.findViewById(R.id.cardView);
        }
    }
}
