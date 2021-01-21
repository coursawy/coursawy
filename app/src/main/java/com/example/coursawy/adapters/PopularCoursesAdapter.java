package com.example.coursawy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursawy.databinding.PopularCourseItemBinding;
import com.example.coursawy.model.Course;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularCoursesAdapter extends RecyclerView.Adapter<PopularCoursesAdapter
        .PopularCoursesViewHolder> {
    private final PopularCourseClickListener listener;
    private List<Course> courseList;

    public PopularCoursesAdapter(PopularCourseClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PopularCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        PopularCourseItemBinding binding = PopularCourseItemBinding.
                inflate(inflater, parent, false);

        return new PopularCoursesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularCoursesViewHolder holder, int position) {
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

    class PopularCoursesViewHolder extends RecyclerView.ViewHolder {
        private final PopularCourseItemBinding binding;

        public PopularCoursesViewHolder(@NonNull PopularCourseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Course course){
            binding.courseImage.setImageResource(course.getCourseImage());
            binding.courseName.setText(course.getCourseName());
            binding.getRoot().setOnClickListener(view -> listener.onClick(course.getCourseName()));
        }
    }
}
