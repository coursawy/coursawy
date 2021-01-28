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

public class CourseProfile extends AppCompatActivity {
    private TabLayout tabLayout_course;
    private ViewPager viewPager;
    private TabItem materials,quizes,exams;
    ImageView back_iv;
    TextView nameCourse;
    public PageAdapter2 pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_profile);
        tabLayout_course = (TabLayout) findViewById(R.id.tablayout_courses);
        materials= (TabItem) findViewById(R.id.materials_course);
        quizes = (TabItem) findViewById(R.id.quizes_course);
        exams = (TabItem) findViewById(R.id.exams_course);
       nameCourse = (TextView) findViewById(R.id.nameCourse);
       Course course = new Course();
       nameCourse.setText(course.getCourseName());
        back_iv = findViewById(R.id.back_iv);
        viewPager = findViewById(R.id.viewPager2);
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pagerAdapter = new PageAdapter2(getSupportFragmentManager(),tabLayout_course.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout_course.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pagerAdapter.notifyDataSetChanged();
                } else if(tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }else if(tab.getPosition()==2){
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
