package com.example.krankbusiness.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import com.example.krankbusiness.databinding.FragmentDashboardBinding;
import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.models.LoanModel;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.UserData;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private KrankViewModel krankViewModel;
    private double totalExpense=0;
    private double monthlyExpense=0;
    private double monthlySell=0;
    private double totalSell=0;
    private double totalCash=0;
    private double totalLoan=0;
    private double totalCapital=0.0;
    private MutableLiveData<Double> capital=new MutableLiveData<>();

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

       // binding.spinKit.setVisibility(View.VISIBLE);

        //Log.e("TAG", "userID: "+firebaseUser.getUid() );


        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());





       binding.thisMonthExp.setText(month_name);
       binding.thisSell.setText(month_name);


       new Thread(() -> new Handler(Looper.getMainLooper()).post(this::getTotalLoan)).start();

       new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> {
           getthismonthSell(month_name);
       })).start();
        new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> {
            getthismonthExpense(month_name);
            //getTotalCash();
        })).start();

        new Thread(() -> new Handler(Looper.getMainLooper()).post(this::getUserInitialData)).start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getTotalCash();
                binding.capital.setText(String.valueOf(totalCapital));
            }
        },1000);





    }

    private void getUserInitialData() {
        krankViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {

                if (userData!=null && !userData.isEmpty()){
                    totalCapital=Double.parseDouble(userData.get(0).getTotalCapital());
                    Log.e("TAG", "onChanged: "+totalCapital );

                }
            }
        });
    }


    private void getTotalLoan() {
        krankViewModel.getLoanList(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<LoanModel>>() {
            @Override
            public void onChanged(List<LoanModel> loanModels) {
                if (loanModels!=null && !loanModels.isEmpty()){
                    for (int i=0;i<loanModels.size();i++){
                        totalLoan=totalLoan+Integer.parseInt(loanModels.get(i).getDueAmount());
                    }

                    binding.totalLoan.setText(String.valueOf(totalLoan));
                }
            }
        });
    }

    private void getTotalCash() {
        totalCash=(totalCapital+totalSell)-totalExpense;
        binding.totalCash.setText(String.valueOf(totalCash));



    }


    private void getthismonthSell(String month_name) {


            krankViewModel.getTotalSell(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
                @Override
                public void onChanged(List<OrderModel> orderModels) {
                    if (orderModels!=null){
                        for (int i=0;i<orderModels.size();i++){

                            if (orderModels.get(i).getDeliveryStatus().equals("Delivered")){

                                totalSell=totalSell+Integer.parseInt(orderModels.get(i).getTotalPrice());
                                binding.totalSell.setText(String.valueOf(totalSell));

                                if (orderModels.get(i).getMonthName()!=null){
                                    if (orderModels.get(i).getMonthName().equals(month_name)){
                                        monthlySell=monthlySell+Double.parseDouble(orderModels.get(i).getTotalPrice());
                                        binding.thisMonthSell.setText(String.valueOf(monthlySell));
                                    }
                                }
                            }

                        }
                    }
                    getTotalCash();

                }
            });


    }

    private void getthismonthExpense(String month_name){
        krankViewModel.getExpenseList(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<ExpenseModel>>() {
            @Override
            public void onChanged(List<ExpenseModel> expenseModels) {

                if (expenseModels!=null & !expenseModels.isEmpty()){

                    for (int i=0;i<expenseModels.size();i++){
                        totalExpense=totalExpense+Integer.parseInt(expenseModels.get(i).getAmount());
                        binding.totalExpense.setText(String.valueOf(totalExpense));
                        if (expenseModels.get(i).getMonthName().equals(month_name)){
                            monthlyExpense = monthlyExpense +Double.parseDouble(expenseModels.get(i).getAmount());
                            binding.thisMonthExpense.setText(String.valueOf(monthlyExpense));
                        }
                    }

                    getTotalCash();
                }
            }
        });
    }
}