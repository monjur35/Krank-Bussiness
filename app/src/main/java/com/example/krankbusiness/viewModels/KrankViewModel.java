package com.example.krankbusiness.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.models.UserData;
import com.example.krankbusiness.repo.FirebaseRepository;

import org.jetbrains.annotations.NotNull;

public class KrankViewModel extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public KrankViewModel(@NonNull @NotNull Application application) {
        super(application);
        firebaseRepository=new FirebaseRepository();

    }
    public MutableLiveData<String>getErrorMsg(){
        return firebaseRepository.errorMsg;
    }
    public MutableLiveData<UserData>getUserData(){
        return firebaseRepository.fetchUserData();
    }

    public void addNewProduct(Product product){
        firebaseRepository.addNewProduct(product);
    }

}
