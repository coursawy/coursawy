package com.example.coursawy.model;

public class Item_Courses {
    private String lecture;

    public Item_Courses(String lecture) {
        this.lecture = lecture;
    }

    public Item_Courses() {
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }
}
