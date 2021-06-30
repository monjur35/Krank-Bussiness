package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankbusiness.R;
import com.example.krankbusiness.databinding.FragmentLoanBinding;
import com.example.krankbusiness.viewModels.KrankViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class LoanFragment extends Fragment {
    private FragmentLoanBinding binding;
    private KrankViewModel krankViewModel;


    public LoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLoanBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(requireActivity()).get(KrankViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loanFragment_to_addLoanFragment);
            }
        });


    }
}