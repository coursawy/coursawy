package com.example.coursawy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coursawy.databinding.CourseItemBinding;
import com.example.coursawy.databinding.PopularCourseItemBinding;
import com.example.coursawy.databinding.QuizeItemBinding;
import com.example.coursawy.model.Course;
import com.google.android.gms.common.util.DataUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    private List<Course> courseList;

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
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourseList(List<Course> courseList){
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    static class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final CourseItemBinding binding;
        public CoursesViewHolder(@NonNull CourseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Course course){
            binding.courseImage.setImageResource(course.getCourseImage());
            binding.courseName.setText(course.getCourseName());
        }
    }




}
