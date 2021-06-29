package com.example.krankbusiness.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankbusiness.R;
import com.example.krankbusiness.adapters.OrderListAdapter;
import com.example.krankbusiness.databinding.FragmentTodayOrderBinding;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);


        krankViewModel.getOrderList(formattedDate).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                if (orderModels!=null){
                    orderModelList.addAll(orderModels);
                    binding.todaysOrderRv.setAdapter(adapter);
                    binding.todaysOrderRv.setLayoutManager(linearLayoutManager);
                    adapter.notifyDataSetChanged();

                }
            }
        });


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

                   krankViewModel.getSearchOrderList(searchWord).observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
                       @Override
                       public void onChanged(List<OrderModel> orderModels) {
                           orderModelList.clear();
                           orderModelList.addAll(orderModels);
                           binding.todaysOrderRv.setAdapter(adapter);
                           binding.todaysOrderRv.setLayoutManager(linearLayoutManager);
                           adapter.notifyDataSetChanged();
                       }
                   });
               }

            }
        });
    }
}