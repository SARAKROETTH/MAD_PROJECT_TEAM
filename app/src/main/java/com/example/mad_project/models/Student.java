package com.example.mad_project.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "studentData")
public class Student {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private String id;
    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("email")
    @ColumnInfo(name = "email")
    private String email;
    @TypeConverters(DateConverter.class)
    @SerializedName("date_of_birth")
    @ColumnInfo(name = "date_of_birth")
    private Date date_of_birth;

    @SerializedName("sex")
    @ColumnInfo(name = "sex")
    private String sex;

    @SerializedName("enrollment")
    @ColumnInfo(name = "enrollment")
    private Boolean enrollment;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private String phone;

    @SerializedName("year_of_study")
    @ColumnInfo(name = "year_of_study")
    private Integer year_of_study;

    @SerializedName("generation")
    @ColumnInfo(name = "generation")
    private String generation;

    @SerializedName("major")
    @ColumnInfo(name = "major")
    private String major;

    @SerializedName("url_image")
    @ColumnInfo(name = "url_image")
    private String url_image;


    public String getUrl_image() {
        return url_image;
    }

    public String getMajor() {
        return major;
    }

    public String getGeneration() {
        return generation;
    }

    public Integer getYear_of_study() {
        return year_of_study;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getEnrollment() {
        return enrollment;
    }

    public String getSex() {
        return sex;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Student( String url_image,Integer year_of_study, String phone, Boolean enrollment, String sex, Date date_of_birth, String email, String name, String id , String generation, String major) {
        this.year_of_study = year_of_study;
        this.phone = phone;
        this.enrollment = enrollment;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.name = name;
        this.id = id;
        this.generation = generation;
        this.major = major;
        this.url_image = url_image;
    }

    public static class DateConverter {
        @TypeConverter
        public static Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public static Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }



}
