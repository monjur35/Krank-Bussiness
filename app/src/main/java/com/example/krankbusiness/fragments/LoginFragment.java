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
import com.example.krankbusiness.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;

    FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentLoginBinding.inflate(inflater);
        mAuth=FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mAuth.signOut();

        if (mAuth.getCurrentUser()!=null){
           homeIntent();
        }
        else {

            binding.loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.spinKit.setVisibility(View.VISIBLE);
                    final String email=binding.loginEmail.getText().toString();
                    final String password=binding.loginEmail.getText().toString();

                    if (!email.isEmpty()||!password.isEmpty()){
                        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                binding.spinKit.setVisibility(View.INVISIBLE);
                                homeIntent();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                binding.spinKit.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        Toast.makeText(getContext(), "Please ,Verify all field", Toast.LENGTH_LONG).show();
                        binding.spinKit.setVisibility(View.INVISIBLE);
                    }
                }
            });

            binding.registratinText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registrationFragment);
                }
            });
        }


    }

    private void homeIntent() {
        Intent home=new Intent(getActivity(), HomeActivity.class);
        startActivity(home);
        requireActivity().finish();
    }
}