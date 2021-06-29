package com.example.krankbusiness.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.models.UserData;
import com.example.krankbusiness.repo.FirebaseRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    public MutableLiveData<List<Product>>getAllProductList(){
        return firebaseRepository.getAllProductList();
    }

    public MutableLiveData<Product>getProductDetails(String productId){
        return firebaseRepository.fetchProductDetailById(productId);
    }
    public void addExpense(ExpenseModel expenseModel){
        firebaseRepository.addExpense(expenseModel);
    }
    public MutableLiveData<List<ExpenseModel>>getExpenseList(){
        return firebaseRepository.getAllExpense();
    }

    public void addOrder(OrderModel orderModel){
        firebaseRepository.addOrder(orderModel);
    }

    public MutableLiveData<List<OrderModel>>getOrderList(String date){
        return firebaseRepository.getAllOrderList(date);
    }

    public MutableLiveData<List<OrderModel>>getSearchOrderList(String data){
        return firebaseRepository.getSearchedOrderList(data);
    }

}
