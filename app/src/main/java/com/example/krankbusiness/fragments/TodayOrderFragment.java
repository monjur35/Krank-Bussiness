package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.krankbusiness.R;
import com.example.krankbusiness.adapters.OrderListAdapter;
import com.example.krankbusiness.databinding.FragmentTodayOrderBinding;
import com.example.krankbusiness.models.OrderModel;
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


public class TodayOrderFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private FragmentTodayOrderBinding binding;
    private KrankViewModel krankViewModel;

    private OrderListAdapter adapter;
    private List<OrderModel>orderModelList;
    private final List<String> stringList = Arrays.asList("All","Today", "Monthly","Pending");

    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();


    public TodayOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentTodayOrderBinding.inflate(inflater);
        krankViewModel=new ViewModelProvider(getActivity()).get(KrankViewModel.class);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        orderModelList=new ArrayList<>();
        adapter=new OrderListAdapter(getContext(),orderModelList);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ArrayAdapter<String> adapterforFilter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, stringList);
        binding.selectedSizeItem1.setAdapter(adapterforFilter);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);

        binding.todaysOrderRv.setAdapter(adapter);
        binding.todaysOrderRv.setLayoutManager(linearLayoutManager);


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        String monthName = new SimpleDateFormat("MMMM").format(c);




        new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> {
            getOrderList();
        })).start();





        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_todayOrderFragment_to_addOrderFragment);
            }
        });

        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               String searchWord=s.toString();
               if (searchWord.isEmpty()){

               }else {

                   krankViewModel.getSearchOrderList(uid,searchWord).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
                       @Override
                       public void onChanged(List<OrderModel> orderModels) {
                           orderModelList.clear();
                           orderModelList.addAll(orderModels);
                           adapter.notifyDataSetChanged();
                       }
                   });
               }

            }
        });

        binding.selectedSizeItem1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position==0){
                        getOrderList();

                    }
                    else if (position==1){
                        getOrderListByDate(formattedDate);

                    }
                    else if (position==2){
                        getOrderListByMonth(monthName);
                    }
                    else {
                        getOrderListByDeliveryStatus("pending");
                    }
            }
        });
    }

    private void getOrderListByDeliveryStatus(String pending) {
        krankViewModel.getOrderListByStatus(uid,pending).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                orderModelList.clear();
                orderModelList.addAll(orderModels);
                adapter.notifyDataSetChanged();
                binding.orderQuantity.setText(String.valueOf(orderModelList.size()));
            }
        });
    }

    private void getOrderList() {
        krankViewModel.getOrderList(uid).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                orderModelList.clear();
                orderModelList.addAll(orderModels);
                adapter.notifyDataSetChanged();
                binding.orderQuantity.setText(String.valueOf(orderModelList.size()));
            }
        });
    }

    private void getOrderListByMonth(String monthName) {
        krankViewModel.getOrderListByMonth(uid,monthName).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                if (orderModels!=null){
                    orderModelList.clear();
                    orderModelList.addAll(orderModels);
                    adapter.notifyDataSetChanged();
                    binding.orderQuantity.setText(String.valueOf(orderModelList.size()));

                }
            }
        });

    }

    private void getOrderListByDate(String date) {
        krankViewModel.getOrderListByDate(uid,date).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                if (orderModels!=null){
                    orderModelList.clear();
                    orderModelList.addAll(orderModels);
                    adapter.notifyDataSetChanged();
                    binding.orderQuantity.setText(String.valueOf(orderModelList.size()));

                }
            }
        });

    }
}