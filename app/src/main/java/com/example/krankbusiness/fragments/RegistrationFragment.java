package com.example.krankbusiness.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankbusiness.R;
import com.example.krankbusiness.activitys.HomeActivity;
import com.example.krankbusiness.databinding.FragmentRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;


public class RegistrationFragment extends Fragment {

    FragmentRegistrationBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser user;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater);
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.reginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.reginBtn.setEnabled(false);
                binding.spinKit.setVisibility(View.VISIBLE);

                final String companyName=binding.companyName.getText().toString();
                final String mobile=binding.mobile.getText().toString();
                final String email=binding.loginEmail.getText().toString();
                final String password=binding.loginpass.getText().toString();

                Bundle bundle=new Bundle();
                bundle.putString("companyName",companyName);
                bundle.putString("mobile",mobile);

                if (!companyName.isEmpty()||!mobile.isEmpty()||!email.isEmpty()||!password.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            binding.spinKit.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), "Registration Success", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_initialDataFragment,bundle);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            binding.spinKit.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            binding.reginBtn.setEnabled(true);
                        }
                    });
                }
                else {
                    binding.spinKit.setVisibility(View.INVISIBLE);
                    binding.reginBtn.setEnabled(true);
                    Toast.makeText(getContext(), "Verify All field", Toast.LENGTH_LONG).show();
                }

            }
        });



    }

}