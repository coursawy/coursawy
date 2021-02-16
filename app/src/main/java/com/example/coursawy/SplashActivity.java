package com.example.coursawy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.coursawy.ui.activities.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this::checkUser,1500);
    }

    private void checkUser() {
        if (firebaseAuth.getCurrentUser() != null) {
            startHomeActivity();
        } else {
            startSignActivity();
        }
    }

    private void startSignActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void startHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
