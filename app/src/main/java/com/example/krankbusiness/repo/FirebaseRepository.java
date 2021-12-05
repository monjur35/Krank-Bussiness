package com.example.krankbusiness.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.krankbusiness.models.ExpenseModel;
import com.example.krankbusiness.models.LoanModel;
import com.example.krankbusiness.models.OrderModel;
import com.example.krankbusiness.models.Product;
import com.example.krankbusiness.models.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseRepository {
    private FirebaseFirestore firebaseFirestore;
    private String currentUser;
    public MutableLiveData<String> errorMsg=new MutableLiveData<>();


    private static String PRODUCTS_COLLECTION="Products";
    private static String EXPENSE_COLLECTION="Expense";
    private static String ORDER_COLLECTION="Order";
    private static String LOANS_COLLECTION="Loans";
    private static String INITIAL_DATA_COLLECTION="UsersData";


    public FirebaseRepository() {
        currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseFirestore=FirebaseFirestore.getInstance();
    }

    public MutableLiveData<String> addUserData(UserData userData){

        final DocumentReference documentReference=firebaseFirestore.collection(INITIAL_DATA_COLLECTION).document();
        MutableLiveData<String>mutableLiveData=new MutableLiveData<>();

        documentReference.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                mutableLiveData.postValue(e.getLocalizedMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<UserData>>getUserData(String uid){
        MutableLiveData<List<UserData>>userDataMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(INITIAL_DATA_COLLECTION).whereEqualTo("uId",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                assert value != null;
                if (!value.isEmpty()){
                    userDataMutableLiveData.postValue(value.toObjects(UserData.class));
                }
            }
        });
        return userDataMutableLiveData;
    }


    public void addNewProduct(Product product){


        final DocumentReference documentReference=firebaseFirestore.collection(PRODUCTS_COLLECTION).document();
        String productID=documentReference.getId();
        product.getSizeLisT().setProductId(productID);
        product.setProductId(productID);

        documentReference.set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });

    }

    public MutableLiveData<List<Product>>getAllProductList(String uid){
        MutableLiveData<List<Product>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(PRODUCTS_COLLECTION).whereEqualTo("uId",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    listMutableLiveData.postValue(value.toObjects(Product.class));
                }
                else {
                    return;
                }
            }
        });

        return listMutableLiveData;
    }

    public MutableLiveData<Product>fetchProductDetailById(String productId){
        MutableLiveData<Product>productMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(PRODUCTS_COLLECTION).document(productId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    errorMsg.postValue(error.getMessage());
                    return;
                }else {
                    if (value!=null){
                        productMutableLiveData.postValue(value.toObject(Product.class));
                    }

                }
            }
        });
        return productMutableLiveData;
    }


    public void addExpense(ExpenseModel expenseModel){
        final DocumentReference documentReference=firebaseFirestore.collection(EXPENSE_COLLECTION).document();
        String expId=documentReference.getId();
        expenseModel.setExpId(expId);

        documentReference.set(expenseModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });

    }

    public MutableLiveData<List<ExpenseModel>>getAllExpense(String uid){
        MutableLiveData<List<ExpenseModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(EXPENSE_COLLECTION).whereEqualTo("userId",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(ExpenseModel.class));
                }
                else {
                    Log.e("TAG", "onEvent: Expemnse List "+error.getLocalizedMessage() );
                }
            }
        });
        return listMutableLiveData;
    }

    public void addOrder(OrderModel orderModel){
        final DocumentReference documentReference=firebaseFirestore.collection(ORDER_COLLECTION).document();
        String orderId=documentReference.getId();
        orderModel.setOderId(orderId);


        documentReference.set(orderModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e("TAG", "onEvent: Add Order List "+e.getLocalizedMessage() );
            }
        });

    }

    public MutableLiveData<List<OrderModel>>getAllOrderListByDate(String  uid,String date){
        MutableLiveData<List<OrderModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).whereEqualTo("userId",uid).whereEqualTo("date",date).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(OrderModel.class));
                }
            }
        });


        return listMutableLiveData;

    }

    public MutableLiveData<List<OrderModel>>getAllOrderListByMonth(String  uid,String month){
        MutableLiveData<List<OrderModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).whereEqualTo("userId",uid).whereEqualTo("monthName",month).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(OrderModel.class));
                }
            }
        });


        return listMutableLiveData;

    }

    public MutableLiveData<List<OrderModel>>getAllOrderListByStatus(String  uid,String status){
        MutableLiveData<List<OrderModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).whereEqualTo("userId",uid).whereEqualTo("deliveryStatus",status).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(OrderModel.class));
                }
            }
        });


        return listMutableLiveData;

    }






    public MutableLiveData<List<OrderModel>>getAllOrderList(String  uid){
        MutableLiveData<List<OrderModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).whereEqualTo("userId",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(OrderModel.class));
                }
            }
        });

        return listMutableLiveData;

    }
    public MutableLiveData<OrderModel>getOrderDetailsById(String orderId){
        MutableLiveData<OrderModel>orderModelMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).document(orderId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error==null && value!=null){
                    orderModelMutableLiveData.postValue(value.toObject(OrderModel.class));
                }
            }
        });
        return orderModelMutableLiveData;
    }



    public void updateDeliveryStatus(String orderId,String status){
        final DocumentReference documentReference=firebaseFirestore.collection(ORDER_COLLECTION).document(orderId);

        Map<String ,Object> map=new HashMap<>();
        map.put("deliveryStatus",status);


        Log.e("TAG2","on failure"+orderId);

        documentReference.update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e("TAG2","onComplete");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG2","on failure  :"+e.getLocalizedMessage());
            }
        });
    }


    public MutableLiveData<List<OrderModel>>getSearchedOrderList(String uid,String searchWord){
        MutableLiveData<List<OrderModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).whereEqualTo("userId",uid).whereEqualTo("customerName",searchWord).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(OrderModel.class));
                }
            }
        });

        return listMutableLiveData;

    }

    public void addLoan(LoanModel loanModel){

        final DocumentReference documentReference=firebaseFirestore.collection(LOANS_COLLECTION).document();
        String loanId=documentReference.getId();
        loanModel.setUserId(currentUser);
       loanModel.setLoanId(loanId);


       documentReference.set(loanModel).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull @NotNull Exception e) {
               Log.e("TAG", "onFailure: Add Loans ::: "+e.getLocalizedMessage() );
           }
       });

    }

    public MutableLiveData<List<LoanModel>>fetchLoanList(String uid){
        MutableLiveData <List<LoanModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(LOANS_COLLECTION).whereEqualTo("userId",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null||!Objects.requireNonNull(value).isEmpty()){
                    listMutableLiveData.postValue(value.toObjects(LoanModel.class));
                }
            }
        });
                return listMutableLiveData;
    }



    public MutableLiveData<List<OrderModel>>getTotalSell(String uid){
        MutableLiveData<List<OrderModel>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(ORDER_COLLECTION).whereEqualTo("userId",uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error==null){
                    assert value != null;
                    listMutableLiveData.postValue(value.toObjects(OrderModel.class));
                }
            }
        });

        return listMutableLiveData;

    }


}
