package com.example.coursawy.model;

public class Item_Tabs {
    private String questions;
        private String subject;
        private String doctorName;
        private String result;

        public Item_Tabs(String questions, String subject, String doctorName, String result) {
            this.questions = questions;
            this.subject = subject;
            this.doctorName = doctorName;
            this.result = result;
        }

        public Item_Tabs() {
        }

        public String getQuestions() {
            return questions;
        }

        public void setQuestions(String questions) {
            this.questions = questions;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
}
