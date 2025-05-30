package com.example.mad_project.models;

public class Suhedule {

    String id;
    String Day;
    String startTime;
    String endTime;
    String teacher;
    Boolean room = false;

    public String getId() {
        return id;
    }

    public String getDay() {
        return Day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTeacher() {
        return teacher;
    }

    public Boolean getRoom() {
        return room;
    }

    public Suhedule(String id, String day, String startTime, String endTime, String teacher, Boolean room) {
        this.id = id;
        Day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.room = room;
    }

}
