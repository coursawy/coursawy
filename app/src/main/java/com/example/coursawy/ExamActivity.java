package com.example.coursawy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends AppCompatActivity {


    @BindView(R.id.online_btn)
    Button onlineBtn;
    @BindView(R.id.offline_btn)
    Button offlineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
//        onlineBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(com.momen.giants.ExamActivity.this, TestsListActivity.class);
//                intent.putExtra("type", "online");
//                startActivity(intent);
//            }
//        });
        offlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this, TestsListActivity.class);
                intent.putExtra("type", "offline");
                startActivity(intent);
            }
        });

    }
}