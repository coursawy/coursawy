package com.example.coursawy.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coursawy.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class AddNewExamFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_new_exam, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        return root;
    }
}