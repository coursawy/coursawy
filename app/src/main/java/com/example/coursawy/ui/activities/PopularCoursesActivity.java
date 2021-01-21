package com.example.coursawy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.coursawy.databinding.ActivityPopularCoursesBinding;

public class PopularCoursesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.coursawy.databinding.ActivityPopularCoursesBinding binding =
                ActivityPopularCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backIv.setOnClickListener(view -> finish());
    }
}