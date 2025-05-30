package com.example.mad_project.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mad_project.models.InformationRupp;
import com.example.mad_project.models.Student;

import java.util.List;

@Dao
public interface MyRoomDao {

    @Query("SELECT * FROM studentData WHERE id = :id")
    Student getStudentById(String id);

    @Query("SELECT * FROM studentData")
    List<Student> getAllStudent();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCategories(Student student);

    @Query("DELETE FROM studentData")
    void deleteAllStident();

    @Query("DELETE FROM studentData WHERE id = :id")
    void deleteCategoryById(String id);


    @Insert
    void insertNotification(InformationRupp informationRupp);

    @Query("SELECT * FROM informationrupp")
    List<InformationRupp> getALLNotification();
}
