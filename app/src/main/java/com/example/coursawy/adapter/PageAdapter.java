package com.example.coursawy.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.coursawy.Public_Exams_Fragment;
import com.example.coursawy.Public_Quizes_Fragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int numftabs;

    public PageAdapter(@NonNull FragmentManager fm, int numofTabs) {
        super(fm);
        this.numftabs = numofTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Public_Quizes_Fragment();
            case 1:
                return new Public_Exams_Fragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
