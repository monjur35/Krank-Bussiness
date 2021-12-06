package com.example.krankbusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankbusiness.R;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.models.SizeList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductListinOrderAdapter extends RecyclerView.Adapter<ProductListinOrderAdapter.ProductListViewHolder> {
    private Context context;
    private List<Product> productList;
    private List<String> sizeLists;


    public ProductListinOrderAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        sizeLists = new ArrayList<>();

    }

    @NonNull
    @NotNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.product_row_in_order, parent, false);
        return new ProductListViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductListinOrderAdapter.ProductListViewHolder holder, int position) {

        String mSize = productList.get(position).getSizeLisT().getmSize();
        sizeLists.add("M :" + mSize);
        String lSize = productList.get(position).getSizeLisT().getlSize();
        sizeLists.add("L :" + lSize);
        String xlSize = productList.get(position).getSizeLisT().getXlSize();
        sizeLists.add("XL :" + xlSize);
        String xxlSize = productList.get(position).getSizeLisT().getXxlSize();
        sizeLists.add("2XL :" + xxlSize);
        String xxxlSize = productList.get(position).getSizeLisT().getXxxlSize();
        sizeLists.add("3XL :" + xxxlSize);


        holder.name.setText(productList.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder {
        AutoCompleteTextView selectedSize;
        NumberPicker numberPicker;
        ImageView productImage;
        TextView name;

        public ProductListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            selectedSize = itemView.findViewById(R.id.selectedsize);
            numberPicker = itemView.findViewById(R.id.numberPiceker);
            productImage = itemView.findViewById(R.id.productImageInOrderRow);
            name = itemView.findViewById(R.id.productNameinOrderRow);
        }
    }
}
