package com.example.coursawy.model;

public class Exam {
    String id, question, ans1, ans2, ans3, ans4, real_answer , course_category , course_name , course_description;
    boolean is_private;

    public Exam() {
    }

    public Exam(String id, String question, String ans1, String ans2, String ans3, String ans4, String real_answer) {
        this.id = id;
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.real_answer = real_answer;
    }

    public Exam(String question, String ans1, String ans2, String ans3, String ans4, String real_answer) {
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.real_answer = real_answer;
    }

    public String getCourse_category() {
        return course_category;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public String getReal_answer() {
        return real_answer;
    }
}
