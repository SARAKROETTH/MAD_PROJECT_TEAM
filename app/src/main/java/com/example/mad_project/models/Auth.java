package com.example.mad_project.models;

import com.google.firebase.auth.FirebaseAuth;

public class Auth {
    private FirebaseAuth mAuth;

    public Auth() {
        mAuth = FirebaseAuth.getInstance();
    }


}
