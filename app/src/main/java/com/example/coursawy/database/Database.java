package com.example.coursawy.database;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Database {
    public static final String USERS_BRANCH = "USERS";
    private static FirebaseFirestore firebaseFirestore;

    private static FirebaseFirestore getInstance(){
        if (firebaseFirestore == null){
            firebaseFirestore = FirebaseFirestore.getInstance();
        }
        return firebaseFirestore;
    }

    public static CollectionReference getUsersReference() {
        return getInstance().collection(USERS_BRANCH);
    }
}
