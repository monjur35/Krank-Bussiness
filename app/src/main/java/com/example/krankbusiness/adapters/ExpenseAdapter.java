package com.example.krankbusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankbusiness.R;
import com.example.krankbusiness.models.ExpenseModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private Context context;
    private List<ExpenseModel>expenseModelList;

    public ExpenseAdapter(Context context, List<ExpenseModel> expenseModelList) {
        this.context = context;
        this.expenseModelList = expenseModelList;
    }

    @NonNull
    @NotNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(context).inflate(R.layout.expense_row,parent,false);

        return new ExpenseViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExpenseAdapter.ExpenseViewHolder holder, int position) {
        holder.expTitle.setText(expenseModelList.get(position).getExpTitle());
        holder.amount.setText(expenseModelList.get(position).getAmount());
        holder.date.setText(expenseModelList.get(position).getDate());
        holder.underOf.setText(expenseModelList.get(position).getUnderOf());
    }

    @Override
    public int getItemCount() {
        return expenseModelList.size();
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView expTitle,amount,date,underOf;
        public ExpenseViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            expTitle=itemView.findViewById(R.id.expenseTitle);
            amount=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date);
            underOf=itemView.findViewById(R.id.undderOf);


        }
    }
}
