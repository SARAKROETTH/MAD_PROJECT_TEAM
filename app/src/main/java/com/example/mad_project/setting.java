package com.example.mad_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.mad_project.databinding.ActivitySettingBinding;
import com.example.mad_project.models.Student;
import com.example.mad_project.repositories.IApiCallback;
import com.example.mad_project.repositories.UserRepository;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.collection.LLRBNode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.http.Url;

public class setting extends AppCompatActivity {

    private ActivitySettingBinding binding;

    private UserRepository userRepository;

    private static final int REQUEST_IMAGE_PICK = 1;

    private Student student;

    private String date;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        LoadStudentData();
    }

    private void LoadStudentData() {
        userRepository.getStudentByid(mAuth.getCurrentUser().getUid(), new IApiCallback<Student>() {
            @Override
            public void onSuccess(Student result) {
               binding.showName.setText(result.getName());
               binding.showSex.setText(result.getSex());
               convertDate(result.getDate_of_birth().toString());
               isEnrolmented(result.getEnrollment());
               binding.showDate.setText(date);
               binding.showPhoneNumber.setText(result.getPhone());
               complateSenten(result.getYear_of_study());
               binding.showGeneration.setText(result.getGeneration());
               binding.showEmail.setText(result.getEmail());
               Loadimage(result.getUrl_image());





            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    private void Loadimage(final String image) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ShapeableImageView imageView = findViewById(R.id.rec_image);

                Glide.with(setting.this)
                        .load(image)
                        .placeholder(R.drawable.profile_icon)
                        .error(R.drawable.profile_icon)
                        .into(imageView);

                runOnUiThread(()->{
                    binding.progressBar.setVisibility(View.GONE);
                    binding.containerProfile.setVisibility(View.VISIBLE);
                });
            }

        });

    }

    private void complateSenten(Integer yearOfStudy) {
        String year = String.format("Year %d Student", yearOfStudy);
        binding.showYear.setText(year);
    }

    private void isEnrolmented(Boolean enrollment) {
        Drawable background = binding.showEnroll.getBackground();
        GradientDrawable drawable = (GradientDrawable) background;

        if(enrollment){
            binding.showEnroll.setText("done");
            drawable.setColor(0xFF00FF00);
        }else {
            binding.showEnroll.setText("Not Enrolled");
            drawable.setColor(0xFFFF0000);
        }

    }

    private void convertDate(String dateOfBirth) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = inputFormat.parse(dateOfBirth);
            this.date = outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        userRepository = new UserRepository(this);

        mAuth = FirebaseAuth.getInstance();

        binding =ActivitySettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


}