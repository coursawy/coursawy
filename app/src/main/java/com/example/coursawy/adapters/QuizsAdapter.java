package com.example.coursawy.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.coursawy.databinding.PopularCourseItemBinding;
import com.example.coursawy.databinding.QuizeItemBinding;
import com.example.coursawy.model.Course;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizsAdapter extends RecyclerView.Adapter<QuizsAdapter.QuizsViewHolder> {
    private List<Course> courseList;
    @NonNull
    @Override
    public QuizsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        QuizeItemBinding binding = QuizeItemBinding.
                inflate(inflater, parent, false);

        return new QuizsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizsViewHolder holder, int position) {
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

    static class QuizsViewHolder extends RecyclerView.ViewHolder {
        private final QuizeItemBinding binding;

        public QuizsViewHolder(@NonNull QuizeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Course course){
            binding.courseImage.setImageResource(course.getCourseImage());
            binding.courseName.setText(course.getCourseName());
        }
    }
}
