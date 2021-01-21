package com.example.coursawy.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.coursawy.Exams_Fragment2;
import com.example.coursawy.Materials_Fragment2;
import com.example.coursawy.Quisez_Fragment2;

public class PageAdapter2 extends FragmentPagerAdapter {
    private int numftabs;

    public PageAdapter2(@NonNull FragmentManager fm, int numofTabs) {
        super(fm);
        this.numftabs = numofTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Materials_Fragment2();
            case 1:
                return new Quisez_Fragment2();
            case 2:
                return new Exams_Fragment2();
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


