package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.krankbusiness.R;
import com.example.krankbusiness.databinding.FragmentAddExpenseBinding;
import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;


public class AddExpenseFragment extends Fragment {
    private FragmentAddExpenseBinding binding;
    private FirebaseAuth firebaseAuth;
    private KrankViewModel krankViewModel;
    private String uId;
    private BottomNavigationView bottomNavigationView;

    private String date="";



    public AddExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentAddExpenseBinding.inflate(inflater);
        firebaseAuth=FirebaseAuth.getInstance();
        krankViewModel=new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);

                date=curDate+"/"+Month+"/"+Year;
            }
        });

        binding.addExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String expTitle=binding.expenseTitle.getText().toString();
                final String amount=binding.amount.getText().toString();
                final String underOf=binding.underOf.getText().toString();
                uId=firebaseAuth.getCurrentUser().getUid();

                if (expTitle.isEmpty()||amount.isEmpty()||underOf.isEmpty()||date.isEmpty()){
                    Toast.makeText(getContext(), "Verify all field ", Toast.LENGTH_SHORT).show();
                }
                else{
                    ExpenseModel expenseModel=new ExpenseModel(uId,null,expTitle,amount,underOf,date);
                    krankViewModel.addExpense(expenseModel);
                    Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_expenseFragment);
                }

            }
        });
    }
}