package com.example.coursawy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coursawy.adapter.PageAdapter2;
import com.example.coursawy.model.Course;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import static com.example.coursawy.ui.fragments.HomeFragment.COURSE_NAME;

public class CourseProfile extends AppCompatActivity {
    private TabLayout tabLayout_course;
    private ViewPager viewPager;
    private TabItem materials, quizes, exams;
    ImageView back_iv , addBtn;
    TextView nameCourse;
    TextView courseOnPhoto;
    public PageAdapter2 pagerAdapter;
    String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_profile);
        courseName = getIntent().getStringExtra(COURSE_NAME);
        tabLayout_course = (TabLayout) findViewById(R.id.tablayout_courses);
        materials = (TabItem) findViewById(R.id.materials_course);
        quizes = (TabItem) findViewById(R.id.quizes_course);
        exams = (TabItem) findViewById(R.id.exams_course);
        nameCourse = (TextView) findViewById(R.id.nameCourse);
        courseOnPhoto = (TextView) findViewById(R.id.course_on_photo);
        Course course = new Course();
        nameCourse.setText(courseName);
        courseOnPhoto.setText(courseName);
        back_iv = findViewById(R.id.back_iv);
        addBtn = findViewById(R.id.add_btn);
        viewPager = findViewById(R.id.viewPager2);
        back_iv.setOnClickListener(v -> finish());
        addBtn.setOnClickListener(view -> {

        });
        pagerAdapter = new PageAdapter2(getSupportFragmentManager(), tabLayout_course.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout_course.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    addBtn.setVisibility(View.VISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    addBtn.setVisibility(View.VISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    addBtn.setVisibility(View.INVISIBLE);
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_course));
    }
}
