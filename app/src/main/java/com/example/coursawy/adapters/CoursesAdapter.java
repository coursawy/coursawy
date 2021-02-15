package com.example.coursawy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coursawy.databinding.CourseItemBinding;
import com.example.coursawy.databinding.PopularCourseItemBinding;
import com.example.coursawy.databinding.QuizeItemBinding;
import com.example.coursawy.model.Course;
import com.example.coursawy.ui.activities.CreateNewRoom;
import com.google.android.gms.common.util.DataUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    private List<Course> courseList;
    private final CoursesClickListner listener4;
    private final Context context;

    public CoursesAdapter(CoursesClickListner listener4, Context context) {
        this.listener4 = listener4;
        this.context = context;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CourseItemBinding binding = CourseItemBinding.
                inflate(inflater, parent, false);

        return new CoursesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.bind(course , position);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final CourseItemBinding binding;

        public CoursesViewHolder(@NonNull CourseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Course course, int position) {
            binding.courseImage.setImageResource(course.getCourseImage());
            binding.courseName.setText(course.getCourseName());
            binding.getRoot().setOnClickListener(view -> listener4.onClick4(course.getCourseName() , position));

        }


    }


}
