package com.example.coursawy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.main_doctor_cardview)
    CardView doctor;
    @BindView(R.id.main_student_cardview)
    @Nullable
    CardView student;
    @BindView(R.id.main_continue_btn)
    Button continuebtn;


    private String job="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.main_doctor_cardview) {
            doctor.setUseCompatPadding(true);
            student.setUseCompatPadding(false);
            continuebtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorParper)));
            job="doctor";
        } else if (view.getId() == R.id.main_student_cardview) {
            doctor.setUseCompatPadding(false);
            student.setUseCompatPadding(true);
            continuebtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorParper)));
            job="student";
        }
        else if (view.getId() == R.id.main_continue_btn) {
            switch (job) {
                case "":
                    Toast.makeText(this, "please choose you are doctor or student", Toast.LENGTH_LONG).show();
                    break;
                case "doctor":
                    startSignActivity(false);
                    break;
                case "student":
                    startSignActivity(true);
                    break;
            }
        }
    }

    private void startSignActivity(boolean isStudent) {
        startActivity(new Intent(this, SignActivity.class).putExtra("isStudent" , isStudent));
    }
}
