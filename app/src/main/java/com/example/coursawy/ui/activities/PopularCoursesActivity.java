package com.example.coursawy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coursawy.CourseProfile;
import com.example.coursawy.R;
import com.example.coursawy.databinding.ActivityPopularCoursesBinding;


public class PopularCoursesActivity extends AppCompatActivity {

    int image;
    String courseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPopularCoursesBinding binding =
                ActivityPopularCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        courseName=getIntent().getStringExtra("name");
        image=getIntent().getIntExtra("image",1);


        binding.popcourseName.setText(courseName);
        binding.popcourseImage.setImageResource(image);

        binding.backIv.setOnClickListener(view -> finish());



    }


}