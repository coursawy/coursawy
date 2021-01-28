package com.example.coursawy.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.coursawy.databinding.PopularTrainersItemBinding;
import com.example.coursawy.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularTrainersAdapter extends RecyclerView.Adapter<PopularTrainersAdapter.PopularTrainersViewHolder> {
    private List<User> userList;
    private final PopularTrainersClickLisner listener2;
    public PopularTrainersAdapter( PopularTrainersClickLisner listener2) {
        this.listener2 = listener2;
    }


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
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(, DoctorInfoActivity.class);
//                intent.putExtra("userid", user.getId());
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

     class PopularTrainersViewHolder extends RecyclerView.ViewHolder {
        private final PopularTrainersItemBinding binding;
        public PopularTrainersViewHolder(@NonNull PopularTrainersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(User user){
            binding.doctorName.setText(user.getUsername());
            binding.getRoot().setOnClickListener(view -> listener2.onClick2(user.getFullname()));
        }
    }
}
