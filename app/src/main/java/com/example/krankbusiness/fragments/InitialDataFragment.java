package com.example.krankbusiness.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankbusiness.activitys.HomeActivity;
import com.example.krankbusiness.databinding.FragmentInitialDataBinding;
import com.example.krankbusiness.models.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;


public class InitialDataFragment extends Fragment {

    FragmentInitialDataBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase firebaseDatabase;




    public InitialDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentInitialDataBinding.inflate(inflater);
        firebaseAuth=FirebaseAuth.getInstance();
        currentUser =firebaseAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle=getArguments();
        final String companyName=bundle.getString("companyName");
        final String mobile=bundle.getString("mobile");

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.spinKit.setVisibility(View.VISIBLE);

                final String capital=binding.capital.getText().toString();
                final String loan=binding.loan.getText().toString();
                final String cash=binding.presentCash.getText().toString();
                final String profit=binding.profit.getText().toString();
                final String expense=binding.totalExpense.getText().toString();

                if (!companyName.isEmpty()||!mobile.isEmpty()||capital.isEmpty()||!loan.isEmpty()||!cash.isEmpty()||!profit.isEmpty()||!expense.isEmpty()){
                    UserData userData=new UserData(currentUser.getUid(),companyName,mobile,capital,loan,cash,profit,expense);
                   // UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(companyName).build();

                    firebaseDatabase.getReference("KrankUsers").child(currentUser.getUid()).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                            binding.spinKit.setVisibility(View.INVISIBLE);

                            homeIntent();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            binding.spinKit.setVisibility(View.INVISIBLE);
                        }
                    });


                }
                else {
                    Toast.makeText(getContext(), "verify all field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void homeIntent() {
        Intent home=new Intent(getActivity(), HomeActivity.class);
        startActivity(home);
        requireActivity().finish();
    }
}