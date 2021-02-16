package com.example.coursawy;

public class ProfileInfoItem {
    private String title;
    private String info;

    public ProfileInfoItem() {
    }

    public ProfileInfoItem(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }
}
