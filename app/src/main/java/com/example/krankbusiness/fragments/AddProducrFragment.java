package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankbusiness.R;
import com.example.krankbusiness.databinding.FragmentAddProducrBinding;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AddProducrFragment extends Fragment {
    private FragmentAddProducrBinding binding;
    private KrankViewModel krankViewModel;

    private BottomNavigationView bottomNavigationView;

    public AddProducrFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAddProducrBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        krankViewModel.getAllProductList().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (products!=null){


                }
            }
        });



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addProducrFragment_to_inputProductFragment);
            }
        });
    }
}