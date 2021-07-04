package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankbusiness.R;
import com.example.krankbusiness.databinding.FragmentDashboardBinding;
import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.models.UserData;
import com.example.krankbusiness.viewModels.KrankViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private KrankViewModel krankViewModel;
    private int totalExpense;




    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDashboardBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(getActivity()).get(KrankViewModel.class);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.spinKit.setVisibility(View.VISIBLE);

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

       getthismonthExpense(month_name);





        krankViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                if (userData!=null){
                    binding.capital.setText(userData.getTotalCapital());
                    binding.profit.setText(userData.getTotalProfit());
                    binding.totalExpense.setText(userData.getTotalExpense());
                    binding.totalCash.setText(userData.getTotalCash());
                    binding.totalLoan.setText(userData.getTotalLoan());

                    binding.spinKit.setVisibility(View.INVISIBLE);


                }
                else {
                    String error= krankViewModel.getErrorMsg().toString();

                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

                    binding.spinKit.setVisibility(View.INVISIBLE);
                }



                if (krankViewModel.getErrorMsg()!=null){
                    String error= krankViewModel.getErrorMsg().toString();
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    binding.spinKit.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    private void getthismonthExpense(String month_name){
        krankViewModel.getExpenseList().observe(getViewLifecycleOwner(), new Observer<List<ExpenseModel>>() {
            @Override
            public void onChanged(List<ExpenseModel> expenseModels) {
                for (int i=0;i<expenseModels.size();i++){
                    Log.e("TAG", "Expense : "+expenseModels.size() );
                    if (expenseModels.get(i).getMonthName().equals(month_name)){
                        totalExpense=totalExpense+Integer.parseInt(expenseModels.get(i).getAmount());
                        binding.thisMonthExpense.setText(String.valueOf(totalExpense));
                    }
                }
            }
        });
    }
}