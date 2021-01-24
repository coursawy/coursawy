package com.example.coursawy.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursawy.ui.activities.PopularCoursesActivity;
import com.example.coursawy.R;
import com.example.coursawy.adapters.CoursesAdapter;
import com.example.coursawy.adapters.PopularCourseClickListener;
import com.example.coursawy.adapters.PopularCoursesAdapter;
import com.example.coursawy.adapters.PopularTrainersAdapter;
import com.example.coursawy.adapters.QuizsAdapter;
import com.example.coursawy.databinding.FragmentHomeBinding;
import com.example.coursawy.model.Course;
import com.example.coursawy.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment implements PopularCourseClickListener {
    private FragmentHomeBinding binding;
    private List<Course> courseList;
    private List<Course> popularCourseList;
    private List<Course> quizList;
    private List<User> userList;
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
        courseList.add(new Course(R.drawable.course_image , "Android Course"));
        courseList.add(new Course(R.drawable.course_image , "Web Course"));
        courseList.add(new Course(R.drawable.course_image , "Flutter Course"));
        courseList.add(new Course(R.drawable.course_image , "ML Course"));
        courseList.add(new Course(R.drawable.course_image , "Algorithms Course"));
    }
    private void setPopularCourseList(){
        popularCourseList = new ArrayList<>();
        popularCourseList.add(new Course(R.drawable.java_svgrepo_com , "Android Course"));
        popularCourseList.add(new Course(R.drawable.java_svgrepo_com , "Web Course"));
        popularCourseList.add(new Course(R.drawable.java_svgrepo_com , "Flutter Course"));
        popularCourseList.add(new Course(R.drawable.java_svgrepo_com , "ML Course"));
        popularCourseList.add(new Course(R.drawable.java_svgrepo_com , "Algorithms Course"));
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
        popularTrainersAdapter = new PopularTrainersAdapter();
        popularTrainersAdapter.setUserList(userList);
        recyclerView.setAdapter(popularTrainersAdapter);
    }

    private void setCoursesAdapter(){
        setCourseList();
        RecyclerView recyclerView = binding.coursesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL , false));
        recyclerView.setHasFixedSize(true);
        coursesAdapter = new CoursesAdapter();
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
        quizsAdapter = new QuizsAdapter();
        quizsAdapter.setCourseList(quizList);
        recyclerView.setAdapter(quizsAdapter);
    }

    @Override
    public void onClick(String courseName) {
        //We have Course Name
        startActivity(new Intent(requireContext() , PopularCoursesActivity.class));
    }
}