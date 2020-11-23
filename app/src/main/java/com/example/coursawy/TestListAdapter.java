package com.example.coursawy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TestListAdapter extends ArrayAdapter<TestInfoItem> {
    Context context;

    public TestListAdapter(Context context, ArrayList<TestInfoItem> testInfoItems) {
        super(context, 0, testInfoItems);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.list_item_test, parent, false);
        TestInfoItem item = getItem(position);
        TextView grade = convertView.findViewById(R.id.test_grade_text_view);
        assert item != null;
        grade.setText(item.getGrade());
        TextView title = convertView.findViewById(R.id.test_title_text_view);
        title.setText(item.getTitle());
        TextView desc = convertView.findViewById(R.id.test_desc_text_view);
        desc.setText(item.getDesc());
        if (item.getDesc().length() == 0)
            desc.setVisibility(View.GONE);
        else
            desc.setVisibility(View.VISIBLE);
        return convertView;
    }
}
