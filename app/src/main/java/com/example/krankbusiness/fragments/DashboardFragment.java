package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    private int totalExpense=0;
    private int monthlyExpense=0;
    private int monthlySell=0;
    private int totalSell=0;
    private int totalCash=0;
    private int totalLoan=0;

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



        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

       getthismonthExpense(month_name);
       getthismonthSell(month_name);
       getTotalCash();


       binding.thisMonthExp.setText(month_name);
       binding.thisSell.setText(month_name);

       new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> {
           getTotalLoan();
       })).start();

       krankViewModel.getUserData(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
           @Override
           public void onChanged(List<UserData> userData) {
               if (userData.size()!=0){
                   UserData user=userData.get(0);
                   binding.capital.setText(user.getTotalCapital());
                   binding.profit.setText(user.getTotalProfit());
                   binding.totalExpense.setText(user.getTotalExpense());
                 //  binding.dashBoardLabel.setText(user.getCompanyName());
                   binding.totalCash.setText(user.getTotalCash());
                   binding.totalLoan.setText(user.getTotalLoan());
                   binding.spinKit.setVisibility(View.INVISIBLE);
               }
           }
       });




        List<Pair<Float,String>> pairList = new ArrayList<>();

        pairList.add(new Pair<>(1f,"fsdfd"));
        pairList.add(new Pair<>(2f,"fsdfd"));
        pairList.add(new Pair<>(3f,"fsdfd"));
        pairList.add(new Pair<>(4f,"fsdfd"));
        pairList.add(new Pair<>(3f,"fsdfd"));



        //binding.barChart.show((LinkedHashMap<String, Float>) pairList);
       // binding.barChart.animate((LinkedHashMap<String, Float>) pairList);


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
        totalCash=totalSell-totalExpense;
        binding.totalCash.setText(String.valueOf(totalCash));
    }


    private void getthismonthSell(String month_name) {

        new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> {
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
                                        monthlySell=monthlySell+Integer.parseInt(orderModels.get(i).getTotalPrice());
                                        binding.thisMonthSell.setText(String.valueOf(monthlySell));
                                    }
                                }
                            }

                        }
                    }
                    getTotalCash();

                }
            });
        })).start();

    }

    private void getthismonthExpense(String month_name){
        krankViewModel.getExpenseList(firebaseUser.getUid()).observe(getViewLifecycleOwner(), new Observer<List<ExpenseModel>>() {
            @Override
            public void onChanged(List<ExpenseModel> expenseModels) {
                Log.e("TAG", "onChanged: "+firebaseUser.getUid() );
                for (int i=0;i<expenseModels.size();i++){
                    Log.e("TAG", "Expense : "+expenseModels.size() );
                    totalExpense=totalExpense+Integer.parseInt(expenseModels.get(i).getAmount());
                    binding.totalExpense.setText(String.valueOf(totalExpense));
                    if (expenseModels.get(i).getMonthName().equals(month_name)){
                        monthlyExpense = monthlyExpense +Integer.parseInt(expenseModels.get(i).getAmount());
                        binding.thisMonthExpense.setText(String.valueOf(monthlyExpense));
                    }
                }

                getTotalCash();
            }
        });
    }
}