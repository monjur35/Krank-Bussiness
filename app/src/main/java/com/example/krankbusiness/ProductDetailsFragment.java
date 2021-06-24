package com.example.krankbusiness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankbusiness.databinding.FragmentProductDetailsBinding;
import com.example.krankbusiness.viewModels.KrankViewModel;

import org.jetbrains.annotations.NotNull;


public class ProductDetailsFragment extends Fragment {
    private  FragmentProductDetailsBinding binding;
    private KrankViewModel krankViewModel;



    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding=FragmentProductDetailsBinding.inflate(inflater);
      krankViewModel=new ViewModelProvider(getActivity()).get(KrankViewModel.class);

      return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.lSizeee.setEnabled(true);
                binding.xlSizeee.setEnabled(true);
                binding.xxlSizeee.setEnabled(true);
                binding.xxxlSizeee.setEnabled(true);
                binding.mSizeee.setEnabled(true);
                binding.editBtn.setVisibility(View.INVISIBLE);
                binding.updateBtn.setVisibility(View.VISIBLE);
            }
        });
    }
}