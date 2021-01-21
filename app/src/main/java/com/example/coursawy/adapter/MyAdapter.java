package com.example.coursawy.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursawy.R;
import com.example.coursawy.model.Item_Tabs;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder >{


    ArrayList<Item_Tabs> list;
    Context context;
    int res;
    public MyAdapter(Context context, ArrayList<Item_Tabs> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.item1,parent,false);
        return new MyAdapter.ViewHolder (view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        res = position;
        holder.questions.setText(list.get(position).getQuestions());
        holder.subject.setText(list.get(position).getSubject());
        holder.doctorName.setText(list.get(position).getDoctorName());
        holder.result.setText(list.get(position).getResult());
        if(holder.result.getText().equals("new")){

        }else if(holder.result.getText().equals("fail")){
            Drawable mDrawable = ContextCompat.getDrawable(context, R.drawable.faill_button);
            holder.result.setBackground(mDrawable);
        }else if (holder.result.getText().equals("pass")){
            Drawable mDrawable = ContextCompat.getDrawable(context, R.drawable.pass_button);
            holder.result.setBackground(mDrawable);
        }

    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questions;
        TextView subject ;
        TextView doctorName ;
        TextView result ;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            questions = (TextView) itemView.findViewById(R.id.questions);
            subject = (TextView) itemView.findViewById(R.id.subject);
            doctorName = (TextView) itemView.findViewById(R.id.doctorName);
            result = (TextView) itemView.findViewById(R.id.passOrfail);

        }
    }
}