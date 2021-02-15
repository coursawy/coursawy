package com.example.coursawy.model;


public class Course {
    private String id;
    private int courseImage;
    private String courseCategory , courseName , courseDescription;


    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(int courseImage, String courseName) {
        this.courseImage = courseImage;
        this.courseName = courseName;
    }

    public Course(String courseCategory, String courseName, String courseDescription) {
        this.courseCategory = courseCategory;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public Course(String id, int courseImage, String courseCategory, String courseName, String courseDescription) {
        this.id = id;
        this.courseImage = courseImage;
        this.courseCategory = courseCategory;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCourseImage() {
        return courseImage;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseImage(int courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
