package com.example.krankbusiness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krankbusiness.databinding.FragmentInputProductBinding;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.viewModels.KrankViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class InputProductFragment extends Fragment {

    private FragmentInputProductBinding binding;
    private KrankViewModel krankViewModel;
    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;


    public InputProductFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInputProductBinding.inflate(inflater);
        krankViewModel = new ViewModelProvider(requireActivity()).get(KrankViewModel.class);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String userId = currentUser.getUid();


        binding.addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.spinKit.setVisibility(View.VISIBLE);

                final String product_name = binding.productName.getText().toString();
                final String product_price = binding.sellingPrice.getText().toString();
                final String production_cost = binding.productincost.getText().toString();
                final String mSize = binding.mSize.getText().toString();
                final String lSize = binding.lSize.getText().toString();
                final String xlSize = binding.xlSize.getText().toString();
                final String xxlSize = binding.xxlSize.getText().toString();
                final String xxxlSize = binding.xxxlSize.getText().toString();



                if (product_name.isEmpty() || product_price.isEmpty() || production_cost.isEmpty() || mSize.isEmpty() ||
                        lSize.isEmpty() || xlSize.isEmpty() || xxlSize.isEmpty() || xxxlSize.isEmpty()) {

                    binding.spinKit.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Verify all field", Toast.LENGTH_SHORT).show();

                } else {



                            final int priceInt = Integer.parseInt(product_price);
                            final int cost = Integer.parseInt(production_cost);
                            final int mSizeInt = Integer.parseInt(mSize);
                            final int lSizeInt = Integer.parseInt(lSize);
                            final int xlSizeInt = Integer.parseInt(xlSize);
                            final int xxlSizeInt = Integer.parseInt(xxlSize);
                            final int xxxlSizeInt = Integer.parseInt(xxxlSize);

                            final int totalinStock = mSizeInt + lSizeInt + xlSizeInt + xxlSizeInt + xxxlSizeInt;

                            Product product = new Product(userId, null, product_name, priceInt, cost,
                                    mSizeInt, lSizeInt, xlSizeInt, xxlSizeInt, xxxlSizeInt, totalinStock);

                            krankViewModel.addNewProduct(product);

                            Toast.makeText(getContext(), krankViewModel.getErrorMsg().toString(), Toast.LENGTH_SHORT).show();

                             binding.spinKit.setVisibility(View.INVISIBLE);

                    Navigation.findNavController(v).navigate(R.id.action_inputProductFragment_to_addProducrFragment);
                }

                Toast.makeText(getContext(), krankViewModel.getErrorMsg().toString(), Toast.LENGTH_SHORT).show();


            }


        });
    }


}