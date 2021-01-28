package com.example.coursawy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import in.aabhasjindal.otptextview.OtpTextView;

//import com.mukesh.OnOtpCompletionListener;
//import com.mukesh.OtpView;

public class ExamCodeActivity extends AppCompatActivity {
    private OtpTextView otpView;
    private Button confirmButton;
    private ImageView backIv;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_code);
        database = FirebaseDatabase.getInstance();
        otpView = findViewById(R.id.otp_view);
        confirmButton = findViewById(R.id.confirm_btn);
        backIv = findViewById(R.id.back_iv);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamCodeActivity.super.onBackPressed();
            }
        });

//        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
//            @Override
//            public void onOtpCompleted(String otp) {
//
//            }
//        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpView.getOTP().length() == 5) {
                    Intent i = new Intent(ExamCodeActivity.this, TestsActivity.class);
                    i.putExtra("type", "online");
                    i.putExtra("unitName", "Test Exam");
                    i.putExtra("id", "Test Exam");
                    startActivity(i);
                } else {
                    Toast.makeText(ExamCodeActivity.this, "Enter a valid code!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}