package com.example.krankbusiness;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.krankbusiness.adapters.ProductListinOrderAdapter;
import com.example.krankbusiness.databinding.FragmentAddOrderBinding;
import com.example.krankbusiness.models.Items;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.models.SizeList;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddOrderFragment extends Fragment {

    private FragmentAddOrderBinding binding;
    private KrankViewModel krankViewModel;
    private List<String> productsName;
    private List<Integer> productsPrice;
    private List<String> sizeList = Arrays.asList("mSize", "lSize", "xlSize", "xxlSize", "xxxlSize");
    private List<Items> itemsList;
    private List<String> productIdList;
    private int totalPrice = 0;
    private Items items1;
    private Items items2;
    private Items items3;
    private Items items4;
    private String item1Name;
    private String item2Name;
    private String item3Name;
    private String item4Name;
    private String item1Size;
    private String item2Size;
    private String item3Size;
    private String item4Size;
    private int selectedItemCount;


    private List<Product> productList;


    private BottomNavigationView bottomNavigationView;
    private String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();


    public AddOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddOrderBinding.inflate(inflater);
        krankViewModel = new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);




        productsName = new ArrayList<>();
        productsPrice = new ArrayList<>();
        items1 = new Items();
        items2 = new Items();
        items3 = new Items();
        items4 = new Items();
        itemsList = new ArrayList<>();
        productIdList = new ArrayList<>();


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        @SuppressLint("SimpleDateFormat")
        String monthName = new SimpleDateFormat("MMMM").format(c);
        Log.e("TAG", "onViewCreated:  Month"+monthName );


        binding.spinKit.setVisibility(View.VISIBLE);
        //  StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL);

        krankViewModel.getAllProductList(uid).observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                for (int i = 0; i < products.size(); i++) {
                    productsName.add(products.get(i).getProductName());
                    productsPrice.add(products.get(i).getProductPrice());
                    productIdList.add(products.get(i).getProductId());
                    // products.get(i).getSizeLisT().getmSize();

                }


                final ArrayAdapter<String> productAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, productsName);
                binding.selectedProductItem1.setAdapter(productAdapter);

                binding.selectedProductItem2.setAdapter(productAdapter);
                binding.selectedProductItem3.setAdapter(productAdapter);
                binding.selectedProductItem4.setAdapter(productAdapter);


                binding.selectedProductItem1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item1Name = productsName.get(position);
                        items1.setItemName(item1Name);
                        totalPrice = productsPrice.get(position);
                        binding.totalPrice.setText(String.valueOf(totalPrice));

                        final String mSize = "M :" + products.get(position).getSizeLisT().getmSize();
                        final String lSize = "L :" + products.get(position).getSizeLisT().getlSize();
                        final String xlSize = "XL :" + products.get(position).getSizeLisT().getXlSize();
                        final String xxlSize = "2xL :" + products.get(position).getSizeLisT().getXxlSize();
                        final String xxxlSize = "3XL :" + products.get(position).getSizeLisT().getXxlSize();

                        Log.e("TAG", "onItemSelected: " + mSize + lSize + xlSize);


                        final List<String> size = Arrays.asList(mSize, lSize, xlSize, xxlSize, xxxlSize);
                        final ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, size);
                        binding.selectedSizeItem1.setAdapter(sizeAdapter);

                        binding.selectedSizeItem1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                item1Size = sizeList.get(position);
                                items1.setItemSize(item1Size);
                                itemsList.add(items1);

                                binding.selectedProductItem2.setEnabled(true);
                                binding.selectedSizeItem2.setEnabled(true);
                            }
                        });
                    }
                });

                binding.selectedProductItem2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item2Name = productsName.get(position);

                        items2.setItemName(item2Name);
                        totalPrice = totalPrice + productsPrice.get(position);
                        binding.totalPrice.setText(String.valueOf(totalPrice));

                        final String mSize = "M :" + products.get(position).getSizeLisT().getmSize();
                        final String lSize = "L :" + products.get(position).getSizeLisT().getlSize();
                        final String xlSize = "XL :" + products.get(position).getSizeLisT().getXlSize();
                        final String xxlSize = "2xL :" + products.get(position).getSizeLisT().getXxlSize();
                        final String xxxlSize = "3XL :" + products.get(position).getSizeLisT().getXxlSize();

                        Log.e("TAG", "onItemSelected: " + mSize + lSize + xlSize);


                        final List<String> size = Arrays.asList(mSize, lSize, xlSize, xxlSize, xxxlSize);
                        final ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, size);
                        binding.selectedSizeItem2.setAdapter(sizeAdapter);

                        binding.selectedSizeItem2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                item2Size = sizeList.get(position);
                                items2.setItemSize(item2Size);
                                itemsList.add(items2);

                                binding.selectedProductItem3.setEnabled(true);
                                binding.selectedSizeItem3.setEnabled(true);
                            }
                        });
                    }
                });

                binding.selectedProductItem3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item3Name = productsName.get(position);

                        items3.setItemName(item3Name);
                        totalPrice = totalPrice + productsPrice.get(position);
                        binding.totalPrice.setText(String.valueOf(totalPrice));

                        final String mSize = "M :" + products.get(position).getSizeLisT().getmSize();
                        final String lSize = "L :" + products.get(position).getSizeLisT().getlSize();
                        final String xlSize = "XL :" + products.get(position).getSizeLisT().getXlSize();
                        final String xxlSize = "2xL :" + products.get(position).getSizeLisT().getXxlSize();
                        final String xxxlSize = "3XL :" + products.get(position).getSizeLisT().getXxlSize();

                        Log.e("TAG", "onItemSelected: " + mSize + lSize + xlSize);


                        final List<String> size = Arrays.asList(mSize, lSize, xlSize, xxlSize, xxxlSize);
                        final ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, size);
                        binding.selectedSizeItem3.setAdapter(sizeAdapter);

                        binding.selectedSizeItem3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                item3Size = sizeList.get(position);
                                items3.setItemSize(item3Size);
                                itemsList.add(items3);

                                binding.selectedProductItem4.setEnabled(true);
                                binding.selectedSizeItem4.setEnabled(true);
                            }
                        });
                    }
                });

                binding.selectedProductItem4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item4Name = productsName.get(position);

                        items4.setItemName(item4Name);
                        totalPrice = totalPrice + productsPrice.get(position);
                        binding.totalPrice.setText(String.valueOf(totalPrice));

                        final String mSize = "M :" + products.get(position).getSizeLisT().getmSize();
                        final String lSize = "L :" + products.get(position).getSizeLisT().getlSize();
                        final String xlSize = "XL :" + products.get(position).getSizeLisT().getXlSize();
                        final String xxlSize = "2xL :" + products.get(position).getSizeLisT().getXxlSize();
                        final String xxxlSize = "3XL :" + products.get(position).getSizeLisT().getXxlSize();

                        Log.e("TAG", "onItemSelected: " + mSize + lSize + xlSize);


                        final List<String> size = Arrays.asList(mSize, lSize, xlSize, xxlSize, xxxlSize);
                        final ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, size);
                        binding.selectedSizeItem4.setAdapter(sizeAdapter);

                        binding.selectedSizeItem4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                item4Size = sizeList.get(position);
                                items4.setItemSize(item4Size);
                                itemsList.add(items4);
                            }
                        });
                    }
                });


                binding.spinKit.setVisibility(View.INVISIBLE);
            }
        });




        binding.addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String customerName = binding.customerName.getText().toString();
                final String customerPhone = binding.phone.getText().toString();
                final String customerAddress = binding.address.getText().toString();


                if (customerName.isEmpty() || customerPhone.isEmpty() || customerAddress.isEmpty() || itemsList.size() == 0) {
                    Toast.makeText(getContext(), "plz ,Verify all field", Toast.LENGTH_SHORT).show();
                } else {

                    OrderModel orderModel = new OrderModel(null, uid, customerName, customerPhone, customerAddress, itemsList, String.valueOf(totalPrice), formattedDate,monthName);
                    krankViewModel.addOrder(orderModel);


                    Navigation.findNavController(v).navigate(R.id.action_addOrderFragment_to_todayOrderFragment);

                }


            }
        });

    }
}