package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankbusiness.R;
import com.example.krankbusiness.adapters.LoanAdapter;
import com.example.krankbusiness.databinding.FragmentLoanBinding;
import com.example.krankbusiness.models.LoanModel;
import com.example.krankbusiness.viewModels.KrankViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LoanFragment extends Fragment {
    private FragmentLoanBinding binding;
    private KrankViewModel krankViewModel;
    private LoanAdapter loanAdapter;
    private List<LoanModel>loanModelList;


    public LoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLoanBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(requireActivity()).get(KrankViewModel.class);
        loanModelList=new ArrayList<>();
        loanAdapter=new LoanAdapter(getContext(),loanModelList);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());


        krankViewModel.getLoanList().observe(getViewLifecycleOwner(), new Observer<List<LoanModel>>() {
            @Override
            public void onChanged(List<LoanModel> loanModels) {
                if (loanModels.size()!=0){
                    loanModelList.addAll(loanModels);
                    binding.loanRv.setLayoutManager(linearLayoutManager);
                    binding.loanRv.setAdapter(loanAdapter);
                    loanAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                }

            }
        });


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loanFragment_to_addLoanFragment);
            }
        });


    }
}