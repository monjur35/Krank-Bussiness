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

import com.example.krankbusiness.R;
import com.example.krankbusiness.adapters.ExpenseAdapter;
import com.example.krankbusiness.databinding.FragmentExpenseBinding;
import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ExpenseFragment extends Fragment {
    private FragmentExpenseBinding binding;
    private BottomNavigationView bottomNavigationView;
    private KrankViewModel krankViewModel;

    private ExpenseAdapter expenseAdapter;
    private List<ExpenseModel> expenseModelList;

    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    public ExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExpenseBinding.inflate(inflater);
        krankViewModel = new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);


        expenseModelList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(getContext(), expenseModelList);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.spinKit.setVisibility(View.VISIBLE);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);

        krankViewModel.getExpenseList(uid).observe(getViewLifecycleOwner(), new Observer<List<ExpenseModel>>() {
            @Override
            public void onChanged(List<ExpenseModel> expenseModels) {
                if (expenseModels!=null){
                    expenseModelList.addAll(expenseModels);
                    binding.expenseRv.setAdapter(expenseAdapter);
                    binding.expenseRv.setLayoutManager(linearLayoutManager);
                    expenseAdapter.notifyDataSetChanged();
                    binding.spinKit.setVisibility(View.INVISIBLE);

                }
            }
        });


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_expenseFragment_to_addExpenseFragment);
            }
        });
    }
}