package com.example.coursawy.model;

public class Notification {
    private int id;
    private int doctorImage;
    private String doctorName;
    private String courseName;
    private String time;

    public Notification() {
    }

    public Notification(String doctorName, String courseName, String time) {
        this.doctorName = doctorName;
        this.courseName = courseName;
        this.time = time;
    }

    public Notification(int id, int doctorImage, String doctorName, String courseName, String time) {
        this.id = id;
        this.doctorImage = doctorImage;
        this.doctorName = doctorName;
        this.courseName = courseName;
        this.time = time;
    }

    public int getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(int doctorImage) {
        this.doctorImage = doctorImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
