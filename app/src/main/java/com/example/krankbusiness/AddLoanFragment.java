package com.example.krankbusiness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.krankbusiness.databinding.FragmentAddLoanBinding;
import com.example.krankbusiness.models.LoanModel;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class AddLoanFragment extends Fragment {

    private FragmentAddLoanBinding binding;
    private KrankViewModel krankViewModel;
    private String date="";
    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();


    public AddLoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAddLoanBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(requireActivity()).get(KrankViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.addLoanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String lenderName=binding.name.getText().toString();
                final String amount=binding.amount.getText().toString();
                final String repayed="00";

                if (amount.isEmpty()||lenderName.isEmpty()||date.isEmpty()){
                    Toast.makeText(getContext(), "Please verify all field and date", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoanModel loanModel=new LoanModel(null,uid,lenderName,amount,repayed,amount);
                    krankViewModel.addLoans(loanModel);
                    Navigation.findNavController(v).navigate(R.id.action_addLoanFragment_to_loanFragment);
                }
            }
        });

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

    }
}