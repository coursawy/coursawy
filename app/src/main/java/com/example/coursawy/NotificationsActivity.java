package com.example.coursawy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coursawy.adapters.CoursesAdapter;
import com.example.coursawy.adapters.NotificationsAdapter;
import com.example.coursawy.databinding.ActivityNotificationsBinding;
import com.example.coursawy.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    private ActivityNotificationsBinding binding;
    private List<Notification>  notificationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backIv.setOnClickListener(view -> finish());
        setNotificationsAdapter();
    }
    private void setNotificationList(){
        notificationList = new ArrayList<>();
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
        notificationList.add(new Notification("Dr. Kareem" , "Android Development Course" , "5 min ago"));
    }
    private void setNotificationsAdapter(){
        setNotificationList();
        RecyclerView recyclerView = binding.notificationRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        NotificationsAdapter notificationsAdapter = new NotificationsAdapter();
        notificationsAdapter.setNotificationList(notificationList);
        recyclerView.setAdapter(notificationsAdapter);
    }
}