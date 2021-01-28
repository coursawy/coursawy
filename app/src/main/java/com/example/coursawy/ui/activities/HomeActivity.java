package com.example.coursawy.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coursawy.ExamTypeActivity;
import com.example.coursawy.MainActivity;
import com.example.coursawy.R;
import com.example.coursawy.SignActivity;
import com.example.coursawy.databinding.ActivityHomeBinding;
import com.example.coursawy.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity  {
    private ActivityHomeBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private NavController navController;
    public static DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    TextView navUsername,navUsertype;
    ImageView navUserImage;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
//        toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.user_name);
        navUserImage = headerView.findViewById(R.id.user_image);
        navUsertype = headerView.findViewById(R.id.textView);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                navUsername.setText(user.getUsername());
                navUsertype.setText(user.getType());

                if (user.getProfileImage().equals("default")){
                    navUserImage.setImageResource(R.mipmap.ic_launcher);
                } else {

                    //change this
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(navUserImage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_log_out, R.id.nav_slideshow)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        navigationView.getMenu().findItem(R.id.nav_log_out).setOnMenuItemClickListener(menuItem -> {
            checkUser();
            return true;
        });
        navigationView.getMenu().findItem(R.id.nav_online_exams).setOnMenuItemClickListener(menuItem -> {
            startActivity(new Intent(this , ExamTypeActivity.class));
            return true;
        });

        openDrawerLayout();
        binding.appBarMain.contentMain.actionNotification.
                setOnClickListener(view -> startNotificationsActivity());


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void openDrawerLayout() {
        binding.appBarMain.contentMain.appBarIcon.
                setOnClickListener(view -> drawer.openDrawer(GravityCompat.START));
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void startNotificationsActivity() {
        startActivity(new Intent(this , NotificationsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notification){
            //go to notification fragment

        }
        return super.onOptionsItemSelected(item);
    }

    private void checkUser() {
        if (user != null){
            showMessage(R.string.log_out, R.string.yes, R.string.no, (dialogInterface, i) -> {
                startSignActivity();
            }, (dialogInterface, i) -> {
                drawer.close();
                navigationView.getMenu().getItem(0).setChecked(true);
            });
        }else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
    }

    private void startSignActivity() {
        auth.signOut();
        startActivity(new Intent(this , MainActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void showMessage(int message ,
                            int positiveMsg , int negativeMsg ,
                            DialogInterface.OnClickListener onClickListenerPositive,
                            DialogInterface.OnClickListener onClickListenerNegative){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(negativeMsg,onClickListenerNegative)
                .setNegativeButton(positiveMsg,onClickListenerPositive)
                .setTitle("Log out")
                .setCancelable(false);
        builder.show();
    }

}