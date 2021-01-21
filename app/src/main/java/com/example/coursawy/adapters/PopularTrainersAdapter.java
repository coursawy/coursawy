package com.example.coursawy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursawy.databinding.PopularTrainersItemBinding;
import com.example.coursawy.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularTrainersAdapter extends RecyclerView.Adapter<PopularTrainersAdapter.PopularTrainersViewHolder> {
    private List<User> userList;

    @NonNull
    @Override
    public PopularTrainersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PopularTrainersItemBinding binding = PopularTrainersItemBinding
                .inflate(inflater , parent, false);
        return new PopularTrainersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTrainersViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    static class PopularTrainersViewHolder extends RecyclerView.ViewHolder {
        private final PopularTrainersItemBinding binding;
        public PopularTrainersViewHolder(@NonNull PopularTrainersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(User user){
            binding.doctorName.setText(user.getName());
        }
    }
}
