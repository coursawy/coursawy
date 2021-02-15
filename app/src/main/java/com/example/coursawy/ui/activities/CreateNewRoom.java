package com.example.coursawy.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.coursawy.R;
import com.example.coursawy.databinding.ActivityCreateNewRoomBinding;
import com.example.coursawy.model.Course;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class CreateNewRoom extends AppCompatActivity implements View.OnClickListener {
    private String selectedCourse , quizTitle , description;
    private int position;
    private String[] coursesCategory;
    private ActivityCreateNewRoomBinding binding;

    private String currentUserID;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNewRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createCoursesCategorySpinner();
    }
    private void createCoursesCategorySpinner() {
        coursesCategory = getResources().getStringArray(R.array.CoursesCategory);
        binding.coursesCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                position = pos;
                selectedCourse = coursesCategory[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancel_btn){
            finish();
        }else if (id == R.id.back_iv){
            finish();
        }else if (id == R.id.add_course_btn){
            addCourseToDatabase();
        }
    }

    private void addCourseToDatabase() {
        quizTitle = binding.courseNameEt.getText().toString();
        description = binding.descriptionEt.getText().toString();
        String randomId = UUID.randomUUID().toString();
        if (validatedData()) {
            Course course = new Course(randomId , 0,selectedCourse, quizTitle, description);
            currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference();
            userRef.child("Users").child(currentUserID).child("MyCourses").child(selectedCourse)
                    .child(randomId)
                    .setValue(course).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(CreateNewRoom.this, "Course added Successfully", Toast.LENGTH_SHORT).show();
                    resetViews();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreateNewRoom.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validatedData(){
        if (selectedCourse.equals(coursesCategory[0])){
            binding.coursesCategorySpinner.requestFocus();
            Toast.makeText(this, "Please select your course category", Toast.LENGTH_SHORT).show();
            return false;
        }else if (quizTitle.isEmpty()){
            binding.courseNameEt.setError("required");
            Toast.makeText(this, "Please enter course name", Toast.LENGTH_SHORT).show();
            return false;
        }else if (description.isEmpty()){
            binding.descriptionEt.setError("required");
            Toast.makeText(this, "Please enter course description", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void resetViews() {
        binding.courseNameEt.setText(null);
        binding.descriptionEt.setText(null);
    }
}