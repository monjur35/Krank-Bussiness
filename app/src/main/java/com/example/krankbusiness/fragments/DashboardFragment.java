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
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.UserData;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private KrankViewModel krankViewModel;
    private int totalExpense;
    private int monthlySell;

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;





    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDashboardBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(requireActivity()).get(KrankViewModel.class);

        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();

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
       getthismonthSell(month_name);

       krankViewModel.getUserData(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
           @Override
           public void onChanged(List<UserData> userData) {
               if (userData.size()!=0){
                   UserData user=userData.get(0);
                   binding.capital.setText(user.getTotalCapital());
                   binding.profit.setText(user.getTotalProfit());
                   binding.totalExpense.setText(user.getTotalExpense());
                   binding.totalCash.setText(user.getTotalCash());
                   binding.totalLoan.setText(user.getTotalLoan());
                   binding.spinKit.setVisibility(View.INVISIBLE);
               }
           }
       });




       /* krankViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                if (userData!=null){



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
        });*/

    }

    private void getthismonthSell(String month_name) {
        krankViewModel.getMonthlySell(firebaseUser.getUid(), month_name).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                if (orderModels!=null){
                    for (int i=0;i<orderModels.size();i++){
                        monthlySell=monthlySell+Integer.parseInt(orderModels.get(i).getTotalPrice());
                        binding.thisMonthSell.setText(String.valueOf(monthlySell));
                    }
                }

            }
        });
    }

    private void getthismonthExpense(String month_name){
        krankViewModel.getExpenseList(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<ExpenseModel>>() {
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