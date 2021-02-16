package com.example.coursawy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.AbstractList;

public class ProfileInfoAdapter extends ArrayAdapter<ProfileInfoItem> {
    public ProfileInfoAdapter(@NonNull Context context, AbstractList<ProfileInfoItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.profile_item_layout, parent, false);
        ProfileInfoItem item = getItem(position);
        TextView titleTv = view.findViewById(R.id.profile_title_tv);
        TextView infoTv = view.findViewById(R.id.profile_info_tv);
        titleTv.setText(item.getTitle());
        infoTv.setText(item.getInfo());
        return view;
    }
}
