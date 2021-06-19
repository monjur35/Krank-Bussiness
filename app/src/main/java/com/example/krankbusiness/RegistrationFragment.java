package com.example.krankbusiness;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krankbusiness.databinding.FragmentRegistrationBinding;

import org.jetbrains.annotations.NotNull;


public class RegistrationFragment extends Fragment {

    FragmentRegistrationBinding binding;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentRegistrationBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
   binding.reginBtn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent home=new Intent(getActivity(),HomeActivity.class);
           startActivity(home);
           getActivity().finish();
       }
   });

    }

}