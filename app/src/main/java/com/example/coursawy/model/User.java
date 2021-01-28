package com.example.coursawy.model;

public class User {
    String username;
    String profileImage;
    String fullname;
    String email;
    String password;
    String dateOfBirth;
    String phoneNumber;
    String faculty;
    String grade;
    String code;
    String type;

    public User(String username, String profileImage, String fullname, String email, String password, String dateOfBirth, String phoneNumber, String faculty, String grade, String code,String type) {
        this.username = username;
        this.profileImage = profileImage;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.grade = grade;
        this.code = code;
        this.type=type;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
