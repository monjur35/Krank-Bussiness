package com.example.krankbusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankbusiness.R;
import com.example.krankbusiness.models.OrderModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {
    private Context context;
    private List<OrderModel>orderModelList;

    public OrderListAdapter(Context context, List<OrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(context).inflate(R.layout.order_row,parent,false);

        return new OrderViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderListAdapter.OrderViewHolder holder, int position) {

        holder.customerName.setText(orderModelList.get(position).getCustomerName());
        holder.items.setText(orderModelList.get(position).getItemName()+"-"+orderModelList.get(position).getItemSize());
        holder.address.setText(orderModelList.get(position).getCustomerAddress());
        holder.phone.setText(orderModelList.get(position).getCustomerPhone());
        holder.totalPrice.setText(orderModelList.get(position).getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView customerName,items,address,phone,totalPrice;

        public OrderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            customerName=itemView.findViewById(R.id.customerNameInRow);
            items=itemView.findViewById(R.id.items);
            address=itemView.findViewById(R.id.addressInrow);
            phone=itemView.findViewById(R.id.phoneInRow);
            totalPrice=itemView.findViewById(R.id.totalPriceinRow);
        }
    }
}
