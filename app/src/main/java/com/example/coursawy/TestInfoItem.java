package com.example.coursawy;

public class TestInfoItem {
    public String id, grade, title, desc;

    public TestInfoItem(String id, String grade, String title, String desc) {
        this.id = id;
        this.grade = grade;
        this.title = title;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public TestInfoItem() {
    }
}
