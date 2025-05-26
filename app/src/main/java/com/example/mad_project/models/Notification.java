package com.example.mad_project.models;

import java.util.Date;
import java.util.UUID;

public class Notification {

    private String id;

    private String content;

    private Date createDate;


    public Date getCreateDate() {
        return createDate;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }


    public Notification(String id, String content, Date createDate) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
    }
}
