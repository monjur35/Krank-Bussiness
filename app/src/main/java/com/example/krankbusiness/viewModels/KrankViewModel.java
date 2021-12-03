package com.example.krankbusiness.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.models.LoanModel;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.models.UserData;
import com.example.krankbusiness.repo.FirebaseRepository;
import com.google.firebase.firestore.auth.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KrankViewModel extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public KrankViewModel(@NonNull @NotNull Application application) {
        super(application);
        firebaseRepository=new FirebaseRepository();

    }

    //User Data
    public MutableLiveData<String> setUserDAta(UserData userData){
        return firebaseRepository.addUserData(userData);
    }
    public MutableLiveData<List<UserData>>getUserData(String uid){
        return firebaseRepository.getUserData(uid);
    }
    public MutableLiveData<String>getErrorMsg(){
        return firebaseRepository.errorMsg;
    }

    //Product

    public void addNewProduct(Product product){
        firebaseRepository.addNewProduct(product);
    }

    public MutableLiveData<List<Product>>getAllProductList(String uid){
        return firebaseRepository.getAllProductList(uid);
    }

    public MutableLiveData<Product>getProductDetails(String productId){
        return firebaseRepository.fetchProductDetailById(productId);
    }

    //Expenses

    public void addExpense(ExpenseModel expenseModel){
        firebaseRepository.addExpense(expenseModel);
    }
    public MutableLiveData<List<ExpenseModel>>getExpenseList(String uid){
        return firebaseRepository.getAllExpense(uid);
    }

    //Order

    public void addOrder(OrderModel orderModel){
        firebaseRepository.addOrder(orderModel);
    }

    public MutableLiveData<List<OrderModel>>getOrderListByDate(String  uid,String date){
        return firebaseRepository.getAllOrderListByDate(uid, date);
    }
    public MutableLiveData<List<OrderModel>>getOrderList(String  uid){
        return firebaseRepository.getAllOrderList(uid);
    }

    public MutableLiveData<OrderModel>getOrderDetailsById(String orderId){
        return firebaseRepository.getOrderDetailsById(orderId);
    }

    public void updateDeliveryStatus(String orderId,String status){
        firebaseRepository.updateDeliveryStatus(orderId, status);
    }

    public MutableLiveData<List<OrderModel>>getSearchOrderList(String uid,String data){
        return firebaseRepository.getSearchedOrderList(uid,data);
    }

    //Loans

    public void addLoans(LoanModel loanModel){
        firebaseRepository.addLoan(loanModel);
    }
    public MutableLiveData<List<LoanModel>>getLoanList(String uid){
        return firebaseRepository.fetchLoanList(uid);
    }

    public MutableLiveData<List<OrderModel>>getTotalSell(String uid){
        return firebaseRepository.getTotalSell(uid);
    }


}
