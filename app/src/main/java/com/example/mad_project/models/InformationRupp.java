package com.example.mad_project.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;


@Entity(tableName = "InformationRupp")
public class InformationRupp {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private String id;

    @SerializedName("content")
    @ColumnInfo(name = "content")
    private String content;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("timeSent")
    @ColumnInfo(name = "timeSent")
    private String timeSent;

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }


    public InformationRupp( String content, String title , String timeSent) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.timeSent = timeSent;
    }

}
