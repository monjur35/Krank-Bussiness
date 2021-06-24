package com.example.krankbusiness.repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

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
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FirebaseRepository {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore firebaseFirestore;
    private String currentUser;
    public MutableLiveData<String> errorMsg=new MutableLiveData<>();


    private static String PRODUCTS_COLLECTION="Products";


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
}
