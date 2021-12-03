package com.example.krankbusiness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankbusiness.databinding.FragmentAddOrderBinding;
import com.example.krankbusiness.databinding.FragmentOderDetailsBinding;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.viewModels.KrankViewModel;


public class OderDetailsFragment extends Fragment {

    private FragmentOderDetailsBinding binding;
    private KrankViewModel krankViewModel;
    private String orderId;
    private OrderModel orderModel;



    public OderDetailsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      binding=FragmentOderDetailsBinding.inflate(inflater);
      krankViewModel=new ViewModelProvider(requireActivity()).get(KrankViewModel.class);
      orderModel=new OrderModel();
      return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        orderId= bundle.getString("orderId");

        krankViewModel.getOrderDetailsById(orderId).observe(getViewLifecycleOwner(), new Observer<OrderModel>() {
            @Override
            public void onChanged(OrderModel orderModel) {
                if (orderModel!=null){
                    binding.CustomerName.setText(orderModel.getCustomerName());
                    binding.phone.setText(orderModel.getCustomerPhone());
                    binding.orderStatus.setText(orderModel.getDeliveryStatus());
                    binding.address.setText(orderModel.getCustomerAddress());
                }
            }
        });

        binding.deliveredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus("Delivered");
                binding.pickedUpButton.setEnabled(false);
                binding.deliveredButton.setEnabled(false);
                binding.returnButton.setEnabled(false);
            }
        });

        binding.pickedUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus("Picked up");
                binding.pickedUpButton.setEnabled(false);
                binding.deliveredButton.setEnabled(true);
                binding.returnButton.setEnabled(true);
            }
        });

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus("Returned");
                binding.pickedUpButton.setEnabled(false);
                binding.deliveredButton.setEnabled(false);
                binding.returnButton.setEnabled(false);
            }
        });




    }

    private void updateStatus(String status) {
        new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> {
            krankViewModel.updateDeliveryStatus(orderId,status);


        })).start();
    }
}