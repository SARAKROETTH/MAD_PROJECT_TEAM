package com.example.mad_project.repositories;

import android.content.Context;

import com.example.mad_project.Dao.AppDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AuthRepository {

   private Context context;

    public AuthRepository( Context context){
        this.context = context;

    }



    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void signIn(String email,String password ,final IApiCallback<FirebaseUser> callback){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                callback.onSuccess(mAuth.getCurrentUser());
            }else{
                callback.onError(task.getException().getMessage());
            }
        });
    }

    public void SignOut(){
        mAuth.signOut();

    }


}
