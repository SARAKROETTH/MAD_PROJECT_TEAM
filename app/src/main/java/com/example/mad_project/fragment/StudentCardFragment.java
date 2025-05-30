package com.example.mad_project.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.bumptech.glide.Glide;
import com.example.mad_project.R;
import com.example.mad_project.databinding.FragmentStudentCardBinding;
import com.example.mad_project.setting;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class StudentCardFragment extends Fragment {

    private FragmentStudentCardBinding binding;

    private static final int PICK_IMAGE_REQUEST = 1;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private boolean LoadImage = false;

    private Uri imageUri;


    public StudentCardFragment() {

    }

    @Override
    public void onStart(){
        super.onStart();
        LoadimageFormFirebase();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!LoadImage){
            LoadimageFormFirebase();
        }
    }

    private void LoadimageFormFirebase() {
        db.collection("imageStudent").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.getResult().exists() && task.getResult() != null){
                String url = task.getResult().getString("url_image");
                if(url != null){
                    binding.showImageCard.setVisibility(View.VISIBLE);
                    binding.cardUpload.setVisibility(View.GONE);
                    ShapeableImageView imageView = binding.recImage;
                    Glide.with(requireContext())
                            .load(url)
                            .placeholder(R.drawable.profile_icon)
                            .error(R.drawable.profile_icon)
                            .into(imageView);
                }else {
                    binding.showImageCard.setVisibility(View.GONE);
                    binding.cardUpload.setVisibility(View.VISIBLE);
                }

            }
        });




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentCardBinding.inflate( inflater, container, false);


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.showImageCard.setVisibility(View.VISIBLE);
                binding.cardUpload.setVisibility(View.GONE);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
//            to upload image
            uploadImageToFirebase();
            binding.showImageCard.setVisibility(View.VISIBLE);
            binding.cardUpload.setVisibility(View.GONE);

        }
    }

    private void uploadImageToFirebase() {

        StorageReference storageRef = FirebaseStorage.getInstance().getReference("uploads");
        String fileName = System.currentTimeMillis() + "." + getFileExtension(imageUri);
        StorageReference fileRef = storageRef.child(fileName);

        fileRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnCompleteListener(uri ->{
                    Map<String, Object> data = new HashMap<>();
                    data.put("url_image", uri.getResult().toString());
                    db.collection("imageStudent").document(mAuth.getCurrentUser().getUid()).set(data);
                    LoadImage = true;
                    LoadimageFormFirebase();
                }));
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = requireActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}