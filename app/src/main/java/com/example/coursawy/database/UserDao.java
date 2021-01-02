package com.example.coursawy.database;

import android.media.MediaPlayer;

import com.example.coursawy.model.Exam;
import com.example.coursawy.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserDao {

    public static final String DOCTOR_DOCUMENT = "doctor";
    public static final String DOCTOR_DATA = "doctorData";
    public static final String EXAMS = "exams";
    public static final String QUESTIONS = "questions";
    public static final String STUDENT_DOCUMENT = "student";
    public static final String STUDENT_DATA = "studentData";
    public static void addStudent(User user ,
                                  OnSuccessListener onSuccessListener,
                                  OnFailureListener onFailureListener){
        Database.getUsersReference()
                .document(STUDENT_DOCUMENT)
                .collection(STUDENT_DATA)
                .document(user.getId())
                .set(user)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public static void getStudent(User user ,
                                  OnCompleteListener<DocumentSnapshot> onCompleteListener){
        Database.getUsersReference()
                .document(STUDENT_DOCUMENT)
                .collection(STUDENT_DATA)
                .document(user.getId())
                .get()
                .addOnCompleteListener(onCompleteListener);
    }
    public static void addDoctor(User user ,
                                  OnSuccessListener onSuccessListener,
                                  OnFailureListener onFailureListener){
        Database.getUsersReference()
                .document(DOCTOR_DOCUMENT)
                .collection(DOCTOR_DATA)
                .document(user.getId())
                .set(user)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public static void getDoctor(User user ,
                                  OnCompleteListener<DocumentSnapshot> onCompleteListener){
        Database.getUsersReference()
                .document(DOCTOR_DOCUMENT)
                .collection(DOCTOR_DATA)
                .document(user.getId())
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

    public static void addExam(Exam exam ,
                               OnSuccessListener onSuccessListener ,
                               OnFailureListener onFailureListener){
        Database.getUsersReference()
                .document(DOCTOR_DOCUMENT)
                .collection(EXAMS)
                .document(exam.getId())
                .set(exam)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
    public static void getExam(String id ,
                               OnCompleteListener<DocumentSnapshot> onCompleteListener){
        Database.getUsersReference()
                .document(DOCTOR_DOCUMENT)
                .collection(EXAMS)
                .document(id)
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

}
