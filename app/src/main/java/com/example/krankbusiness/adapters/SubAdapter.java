package com.example.krankbusiness.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankbusiness.R;
import com.example.krankbusiness.models.Items;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.SubViewHolder> {
    private List<Items>itemsList;

    public SubAdapter(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @NotNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemss= LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_item_row,parent,false);
        return new SubViewHolder(itemss);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SubAdapter.SubViewHolder holder, int position) {
        holder.id.setText(position + 1 + ".");
        holder.itemName.setText(itemsList.get(position).getItemName());
        holder.size.setText(itemsList.get(position).getItemSize());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder{
        TextView itemName,size,id;

        public SubViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.itemsNameInSubRow);
            size=itemView.findViewById(R.id.sizeInSubRow);
            id=itemView.findViewById(R.id.id);
        }
    }
}
