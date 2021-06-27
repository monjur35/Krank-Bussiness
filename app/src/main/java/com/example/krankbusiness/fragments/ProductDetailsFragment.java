package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankbusiness.databinding.FragmentProductDetailsBinding;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.viewModels.KrankViewModel;

import org.jetbrains.annotations.NotNull;


public class ProductDetailsFragment extends Fragment {
    private FragmentProductDetailsBinding binding;
    private KrankViewModel krankViewModel;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailsBinding.inflate(inflater);
        krankViewModel = new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.spinKit.setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        String proudctId = bundle.getString("productId");

        if (proudctId != null) {
            krankViewModel.getProductDetails(proudctId).observe(getViewLifecycleOwner(), new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    if (product != null) {

                        binding.productNameindetails.setText(product.getProductName());
                        binding.totalinStock.setText(String.valueOf(product.getTotalInStock()));
                        binding.sellingPriceinDetails.setText(String.valueOf(product.getProductPrice()));
                        binding.productionCostindetails.setText(String.valueOf(product.getProduction_cost()));
                        binding.mSizeee.setText(String.valueOf(product.getmSizeInStock()));
                        binding.lSizeee.setText(String.valueOf(product.getLSizeInStock()));
                        binding.xlSizeee.setText(String.valueOf(product.getXlSizeInStock()));
                        binding.xxlSizeee.setText(String.valueOf(product.getXxlSizeInStock()));
                        binding.xxxlSizeee.setText(String.valueOf(product.getXxxlSizeInStock()));
                        binding.spinKit.setVisibility(View.INVISIBLE);

                    } else {

                        Toast.makeText(getContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
                        binding.spinKit.setVisibility(View.INVISIBLE);

                    }
                }
            });
        }


        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.lSizeee.setEnabled(true);
                binding.xlSizeee.setEnabled(true);
                binding.xxlSizeee.setEnabled(true);
                binding.xxxlSizeee.setEnabled(true);
                binding.mSizeee.setEnabled(true);
                binding.sellingPriceinDetails.setEnabled(true);
                binding.productionCostindetails.setEnabled(true);
                binding.editBtn.setVisibility(View.INVISIBLE);
                binding.updateBtn.setVisibility(View.VISIBLE);
            }
        });
    }
}