package com.example.krankbusiness.repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.krankbusiness.models.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
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

import org.jetbrains.annotations.NotNull;

public class FirebaseRepository {
    private FirebaseDatabase firebaseDatabase;
    private String currentUser;
    public MutableLiveData<String> errorMsg;


    public FirebaseRepository() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
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
}
