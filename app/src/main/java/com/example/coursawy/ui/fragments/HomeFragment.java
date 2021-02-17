package com.example.coursawy.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursawy.CourseProfile;
import com.example.coursawy.DoctorInfoActivity;
import com.example.coursawy.Quiz_ExamActivity;
import com.example.coursawy.adapters.CoursesClickListner;
import com.example.coursawy.adapters.PopularTrainersClickLisner;
import com.example.coursawy.adapters.QuizClickLisner;
import com.example.coursawy.databinding.FragmentHomeBinding;
import com.example.coursawy.ui.activities.CreateNewRoom;
import com.example.coursawy.ui.activities.PopularCoursesActivity;
import com.example.coursawy.R;
import com.example.coursawy.adapters.CoursesAdapter;
import com.example.coursawy.adapters.PopularCourseClickListener;
import com.example.coursawy.adapters.PopularCoursesAdapter;
import com.example.coursawy.adapters.PopularTrainersAdapter;
import com.example.coursawy.adapters.QuizsAdapter;

import com.example.coursawy.model.Course;
import com.example.coursawy.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment implements PopularCourseClickListener, PopularTrainersClickLisner , QuizClickLisner, CoursesClickListner {
    private FragmentHomeBinding binding;
    private List<Course> courseList;
    private List<Course> popularCourseList;
    private List<Course> quizList;
    private List<User> userList;
    public static final String COURSE_NAME = "courseName";
    public static final String COURSE_IMAGE = "courseImage";
    public static final String DOCTOR_NAME = "doctorName";
    CoursesAdapter coursesAdapter;
    PopularCoursesAdapter popularCoursesAdapter;
    QuizsAdapter quizsAdapter;
    PopularTrainersAdapter popularTrainersAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater , container , false);


        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        try {
            setCoursesAdapter();
            setPopularCoursesAdapter();
            setQuizsAdapter();
            setPopularTrainersAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUserList(){
        userList = new ArrayList<>();
        userList.add(new User("Dr. Elon Mask"));
        userList.add(new User("Dr. Elon Mask"));
        userList.add(new User("Dr. Elon Mask"));
        userList.add(new User("Dr. Elon Mask"));
        userList.add(new User("Dr. Elon Mask"));
        userList.add(new User("Dr. Elon Mask"));
    }

    private void setCourseList(){
        courseList = new ArrayList<>();
        courseList.add(new Course(R.drawable.android_logo , "Android Course"));
        courseList.add(new Course(R.drawable.web_icon , "Web Development Course"));
        courseList.add(new Course(R.drawable.ic_flutter_icon , "Flutter Course"));
        courseList.add(new Course(R.drawable.ml_icon , "ML Course"));
        courseList.add(new Course(R.drawable.algorithm_icon , "Algorithms Course"));
        courseList.add(courseList.size() , new Course(R.drawable.add_new_course_recycler_btn , "Add New Course"));
    }
    private void setPopularCourseList(){
        popularCourseList = new ArrayList<>();
        popularCourseList.add(new Course(R.drawable.android_logo , "Android Course"));
        popularCourseList.add(new Course(R.drawable.web_icon , "Web Course"));
        popularCourseList.add(new Course(R.drawable.ic_flutter_icon , "Flutter Course"));
        popularCourseList.add(new Course(R.drawable.ml_icon , "ML Course"));
        popularCourseList.add(new Course(R.drawable.android_logo , "Algorithms Course"));
    }
    private void setQuizList(){
        quizList = new ArrayList<>();
        quizList.add(new Course(R.drawable.group_quizs , "Android Course"));
        quizList.add(new Course(R.drawable.group_quizs , "Web Course"));
        quizList.add(new Course(R.drawable.group_quizs , "Flutter Course"));
        quizList.add(new Course(R.drawable.group_quizs , "ML Course"));
        quizList.add(new Course(R.drawable.group_quizs , "Algorithms Course"));
    }

    private void setPopularTrainersAdapter(){
        setUserList();
        RecyclerView recyclerView = binding.popularTrainersRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL , false));
        recyclerView.setHasFixedSize(true);
        popularTrainersAdapter = new PopularTrainersAdapter(this);
        popularTrainersAdapter.setUserList(userList);
        recyclerView.setAdapter(popularTrainersAdapter);
    }

    private void setCoursesAdapter(){
        setCourseList();
        RecyclerView recyclerView = binding.coursesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL , false));
        recyclerView.setHasFixedSize(true);
        coursesAdapter = new CoursesAdapter(this,requireContext());
        coursesAdapter.setCourseList(courseList);
        recyclerView.setAdapter(coursesAdapter);
    }

    private void setPopularCoursesAdapter(){
        setPopularCourseList();
        RecyclerView recyclerView = binding.popularCoursesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL , false));
        recyclerView.setHasFixedSize(true);
        popularCoursesAdapter = new PopularCoursesAdapter(this);
        popularCoursesAdapter.setCourseList(popularCourseList);
        recyclerView.setAdapter(popularCoursesAdapter);
    }

    private void setQuizsAdapter(){
        setQuizList();
        RecyclerView recyclerView = binding.quizesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL , false));
        recyclerView.setHasFixedSize(true);
        quizsAdapter = new QuizsAdapter(this);
        quizsAdapter.setCourseList(quizList);
        recyclerView.setAdapter(quizsAdapter);
    }

    @Override
    public void onClick(String courseName,int image) {
        //We have Course Name
        Intent intent=new Intent(requireContext() , PopularCoursesActivity.class);

        intent.putExtra("name",courseName);
        intent.putExtra("image",image);
        startActivity(intent);    }
    @Override
    public void onClick2(String id , String doctorName) {
        //We have Course Name
        startActivity(new Intent(requireContext() , DoctorInfoActivity.class));
    }

    @Override
    public void onClick3(String courseName) {

    }

    @Override
    public void onClick4(String courseName , int position,int courseImage) {
        if (position == courseList.size() - 1){
            startCreateNewRoomActivity();
        }else {
            Intent i = new Intent(requireContext(), CourseProfile.class);
            i.putExtra(COURSE_NAME , courseName);
            i.putExtra(COURSE_IMAGE,courseImage);
            startActivity(i);
        }
    }
    private void startCreateNewRoomActivity() {
        startActivity(new Intent(requireContext(), CreateNewRoom.class));
    }
}