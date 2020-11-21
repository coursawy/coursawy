package com.example.coursawy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentOrDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.doctor)
    TextView doctor;
    @BindView(R.id.student)
    @Nullable
    TextView student;

    //declare
//    boolean isStudent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_or_doctor);

        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.doctor) {
            startSignActivity(false);
        } else if (view.getId() == R.id.student) {
            startSignActivity(true);
        }
    }

    private void startSignActivity(boolean isStudent) {
        startActivity(new Intent(this, SignActivity.class).putExtra("isStudent" , isStudent));
    }

}