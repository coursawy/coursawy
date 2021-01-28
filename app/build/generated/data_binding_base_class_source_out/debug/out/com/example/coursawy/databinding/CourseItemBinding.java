// Generated by view binder compiler. Do not edit!
package com.example.coursawy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.coursawy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CourseItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView courseImage;

  @NonNull
  public final TextView courseName;

  private CourseItemBinding(@NonNull CardView rootView, @NonNull ImageView courseImage,
      @NonNull TextView courseName) {
    this.rootView = rootView;
    this.courseImage = courseImage;
    this.courseName = courseName;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CourseItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CourseItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.course_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CourseItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.course_image;
      ImageView courseImage = rootView.findViewById(id);
      if (courseImage == null) {
        break missingId;
      }

      id = R.id.course_name;
      TextView courseName = rootView.findViewById(id);
      if (courseName == null) {
        break missingId;
      }

      return new CourseItemBinding((CardView) rootView, courseImage, courseName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}