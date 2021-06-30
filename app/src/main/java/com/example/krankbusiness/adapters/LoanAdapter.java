package com.example.krankbusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krankbusiness.R;
import com.example.krankbusiness.models.LoanModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoansViewHolder >{

    private Context context;
    private List<LoanModel>loanModelList;

    public LoanAdapter(Context context, List<LoanModel> loanModelList) {
        this.context = context;
        this.loanModelList = loanModelList;
    }

    @NonNull
    @NotNull
    @Override
    public LoansViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(context).inflate(R.layout.loan_row,parent,false);

        return new LoansViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LoanAdapter.LoansViewHolder holder, int position) {
        holder.name.setText(loanModelList.get(position).getLenderName());
        holder.amount.setText(loanModelList.get(position).getAmount());
        holder.repayed.setText(loanModelList.get(position).getRePayedAmount());
        holder.due.setText(loanModelList.get(position).getDueAmount());

    }

    @Override
    public int getItemCount() {
        return loanModelList.size();
    }

    class LoansViewHolder extends RecyclerView.ViewHolder{
        TextView name,amount,repayed,due;

        public LoansViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.lenderTitle);
            amount=itemView.findViewById(R.id.amountInLoanROw);
            repayed=itemView.findViewById(R.id.repayed);
            due=itemView.findViewById(R.id.due);
        }
    }
}
