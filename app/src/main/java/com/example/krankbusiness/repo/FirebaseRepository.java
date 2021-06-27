package com.example.krankbusiness.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.krankbusiness.models.ExpenseModel;
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

import java.util.List;
import java.util.Objects;

public class FirebaseRepository {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore firebaseFirestore;
    private String currentUser;
    public MutableLiveData<String> errorMsg=new MutableLiveData<>();


    private static String PRODUCTS_COLLECTION="Products";
    private static String EXPENSE_COLLECTION="Expense";


    public FirebaseRepository() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseFirestore=FirebaseFirestore.getInstance();
    }

    public MutableLiveData<UserData>fetchUserData(){

        MutableLiveData<UserData>userDataMutableLiveData=new MutableLiveData<>();
        DatabaseReference root=firebaseDatabase.getReference("KrankUsers");
        Query query=root.child(currentUser);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userDataMutableLiveData.postValue(snapshot.getValue(UserData.class));;
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                errorMsg.postValue(error.getMessage());
            }
        });


        return userDataMutableLiveData;
    }


    public void addNewProduct(Product product){


        final DocumentReference documentReference=firebaseFirestore.collection(PRODUCTS_COLLECTION).document();
        String productID=documentReference.getId();
        product.setProductId(productID);

        documentReference.set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });

    }

    public MutableLiveData<List<Product>>getAllProductList(){
        MutableLiveData<List<Product>>listMutableLiveData=new MutableLiveData<>();
        firebaseFirestore.collection(PRODUCTS_COLLECTION).whereEqualTo("uId",currentUser).addSnapshotListener(new EventListener<QuerySnapshot>() {
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

}
