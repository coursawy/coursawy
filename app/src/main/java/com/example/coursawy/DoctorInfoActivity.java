package com.example.coursawy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coursawy.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorInfoActivity extends AppCompatActivity {
    private FirebaseUser user;
    DatabaseReference reference;
    TextView Doctorname,joinDate,numOfCourses,specialist,numOfExams,experience,university,faculty;
    ImageView profileImage,back_iv;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        Doctorname = findViewById(R.id.doctorName);
        profileImage = findViewById(R.id.profile_imagee);
        back_iv = findViewById(R.id.back_iv);
        joinDate = findViewById(R.id.joinDate);
        numOfCourses = findViewById(R.id.numOfCourses);
        specialist = findViewById(R.id.specialist);
        numOfExams = findViewById(R.id. numOfExams);
        university = findViewById(R.id.university);
        experience = findViewById(R.id.experience);
        faculty = findViewById(R.id.faculty);
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Users").child("mQPx2OoT38MAUYRtLIzsKNW0csr1");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Doctorname.setText("Dr."+user.getFullname());
                joinDate.setText(user.getDateOfBirth());
                numOfCourses.setText("5");
                specialist.setText("programming");
                numOfExams.setText("22");
                experience.setText("15 years");
                university.setText("beni suef");
                faculty.setText(user.getFaculty());

                if (user.getProfileImage().equals("default")){
                   profileImage.setImageResource(R.mipmap.ic_launcher);
                } else {

                    //change this
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(profileImage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}