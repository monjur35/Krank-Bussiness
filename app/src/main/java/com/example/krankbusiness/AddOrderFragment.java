package com.example.krankbusiness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.krankbusiness.adapters.ProductListinOrderAdapter;
import com.example.krankbusiness.databinding.FragmentAddOrderBinding;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddOrderFragment extends Fragment {

    private FragmentAddOrderBinding binding;
    private KrankViewModel krankViewModel;
    private List<String>productsName;
    private List<String>size= Arrays.asList(new String[]{"M", "L", "XL","2XL","3XL"});
    private String itemName;


    private List<Product>productList;


    private BottomNavigationView bottomNavigationView;


    public AddOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAddOrderBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);

        productsName=new ArrayList<>();


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.spinKit.setVisibility(View.VISIBLE);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL);

        krankViewModel.getAllProductList().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                for (int i=0;i<products.size();i++){
                    productsName.add(products.get(i).getProductName());
                }

                final ArrayAdapter<String> productAdapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, productsName);
                binding.selectedProduct.setAdapter(productAdapter);

                final ArrayAdapter<String> sizeAdapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, size);
                binding.selectedSize.setAdapter(sizeAdapter);

                binding.spinKit.setVisibility(View.INVISIBLE);
            }
        });


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);


        binding.addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String customerName=binding.customerName.getText().toString();
                final String customerPhone=binding.phone.getText().toString();
                final String customerAddress=binding.address.getText().toString();


               /* if (customerName.isEmpty()||customerPhone.isEmpty()||customerAddress.isEmpty()||itemName.isEmpty()||itemSize.isEmpty()||totalPrice.isEmpty()){
                    Toast.makeText(getContext(), "plz ,Verify all field", Toast.LENGTH_SHORT).show();
                }else {

                    OrderModel orderModel=new OrderModel(null,null,customerName,customerPhone,customerAddress,itemName,itemSize,totalPrice,formattedDate);
                    krankViewModel.addOrder(orderModel);
                    Navigation.findNavController(v).navigate(R.id.action_addOrderFragment_to_todayOrderFragment);

                }*/

            }
        });
    }
}