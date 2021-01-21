package com.example.coursawy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursawy.R;
import com.example.coursawy.model.Item_Courses;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder >{


    ArrayList<Item_Courses> list;
    Context context;
    int res;
    public CoursesAdapter(Context context, ArrayList<Item_Courses> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.item_course,parent,false);
        return new CoursesAdapter.ViewHolder (view);
    }


    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        res = position;
        holder.lecture.setText(list.get(position).getLecture());
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lecture;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            lecture = (TextView) itemView.findViewById(R.id.lecture);

        }
    }
}
