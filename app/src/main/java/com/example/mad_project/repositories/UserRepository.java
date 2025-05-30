package com.example.mad_project.repositories;

import android.content.Context;

import com.example.mad_project.Dao.AppDatabase;
import com.example.mad_project.Dao.MyRoomDao;
import com.example.mad_project.models.Student;
import com.example.mad_project.util.NetworkUtil;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class UserRepository {

    private Context context;

    private MyRoomDao myRoomDao;

    private Student student;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public UserRepository( Context context){
        this.context = context;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        myRoomDao = appDatabase.userDao();
    }


    public void getStudent(String id,  IApiCallback<Student> callback){
        db.collection("studentData").document(id).get().addOnCompleteListener(task -> {
            if(task.isSuccessful() && task.getResult().exists() && task.getResult() != null){
                Date date_of_birth = task.getResult().getTimestamp("date_of_birth").toDate();
                String email = task.getResult().getString("email");
                String name = task.getResult().getString("name");
                String sex = task.getResult().getString("sex");
                Boolean enrollment = task.getResult().getBoolean("enrollment");
                String phone = task.getResult().getString("phone");
                Integer year_of_study = task.getResult().getLong("year_of_study").intValue();
                String generation = task.getResult().getLong("generation").toString();
                String major = task.getResult().getString("major");
                String url_image = task.getResult().getString("url_image");
            student = new Student(url_image,year_of_study,phone,enrollment,sex,date_of_birth,email,name,id,generation,major);
                saveStudentToLocal(student);
            callback.onSuccess(student);
            }else {
                String errorMessage = (task.getException() != null) ? task.getException().getMessage() : "Student not found";
                callback.onError(errorMessage);
            }
        });
    }
    public void deleteStudentById(String id, IApiCallback<Void> callback){
        Executors.newSingleThreadExecutor().execute(() -> {
            myRoomDao.deleteCategoryById(id);
            callback.onSuccess(null);
        });
    }


    public void getStudentByid(String id,IApiCallback<Student> callback){
        Executors.newSingleThreadExecutor().execute(()->{
           Student student = myRoomDao.getStudentById(id);
           callback.onSuccess(student);
        });
    }

    public void saveStudentToLocal(Student student){
        SaveStudentToLocal(student);
    }

    private void SaveStudentToLocal(Student student) {

        Executors.newSingleThreadExecutor().execute(() -> {
            myRoomDao.insertCategories(student);
        });
    }


}
