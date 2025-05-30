package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mad_project.databinding.ActivityProfilePageBinding;
import com.example.mad_project.models.Student;
import com.example.mad_project.repositories.AuthRepository;
import com.example.mad_project.repositories.IApiCallback;
import com.example.mad_project.repositories.UserRepository;

import com.example.mad_project.util.NetworkUtil;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Date;

public class Profile_page extends AppCompatActivity {

    private ActivityProfilePageBinding binding;

    private AuthRepository authRepository;

    private Student student;


    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        userRepository = new UserRepository(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.groupLogout.setOnClickListener(view -> {

            ClearUserData(mAuth.getCurrentUser().getUid());

        });

        binding.groupProfile.setOnClickListener(view -> {
            Intent profileintent = new Intent(this, setting.class);
            startActivity(profileintent);
            finish();
        });

        LoadUserData();

    }

    private void ClearUserData(String nameId) {
        userRepository.deleteStudentById(nameId, new IApiCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                if(result == null){
                    mAuth.signOut();
                    Intent welcomeintent = new Intent(Profile_page.this, WelcomeActivity.class);
                    startActivity(welcomeintent);
                    finish();
                }
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText( Profile_page.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void LoadUserData() {
        if (NetworkUtil.isNetworkAvailable(this)) {
            userRepository.getStudent(mAuth.getCurrentUser().getUid(), new IApiCallback<Student>() {

                @Override
                public void onSuccess(Student result) {
                    binding.textname.setText(result.getName());
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                }

            });
        }else {
            userRepository.getStudentByid(mAuth.getCurrentUser().getUid(), new IApiCallback<Student>() {
                @Override
                public void onSuccess(Student result) {
                    binding.textname.setText(result.getName());
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                }
            });

        }

    }




}