// Generated by view binder compiler. Do not edit!
package com.example.coursawy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.coursawy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final RecyclerView coursesRecyclerView;

  @NonNull
  public final RecyclerView popularCoursesRecyclerView;

  @NonNull
  public final RecyclerView popularTrainersRecyclerView;

  @NonNull
  public final RecyclerView quizesRecyclerView;

  @NonNull
  public final TextView textCourses;

  @NonNull
  public final TextView textPopularCourses;

  @NonNull
  public final TextView textPopularTrainers;

  @NonNull
  public final TextView textQuizes;

  private FragmentHomeBinding(@NonNull NestedScrollView rootView,
      @NonNull RecyclerView coursesRecyclerView, @NonNull RecyclerView popularCoursesRecyclerView,
      @NonNull RecyclerView popularTrainersRecyclerView, @NonNull RecyclerView quizesRecyclerView,
      @NonNull TextView textCourses, @NonNull TextView textPopularCourses,
      @NonNull TextView textPopularTrainers, @NonNull TextView textQuizes) {
    this.rootView = rootView;
    this.coursesRecyclerView = coursesRecyclerView;
    this.popularCoursesRecyclerView = popularCoursesRecyclerView;
    this.popularTrainersRecyclerView = popularTrainersRecyclerView;
    this.quizesRecyclerView = quizesRecyclerView;
    this.textCourses = textCourses;
    this.textPopularCourses = textPopularCourses;
    this.textPopularTrainers = textPopularTrainers;
    this.textQuizes = textQuizes;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.courses_recycler_view;
      RecyclerView coursesRecyclerView = rootView.findViewById(id);
      if (coursesRecyclerView == null) {
        break missingId;
      }

      id = R.id.popular_courses_recycler_view;
      RecyclerView popularCoursesRecyclerView = rootView.findViewById(id);
      if (popularCoursesRecyclerView == null) {
        break missingId;
      }

      id = R.id.popular_trainers_recycler_view;
      RecyclerView popularTrainersRecyclerView = rootView.findViewById(id);
      if (popularTrainersRecyclerView == null) {
        break missingId;
      }

      id = R.id.quizes_recycler_view;
      RecyclerView quizesRecyclerView = rootView.findViewById(id);
      if (quizesRecyclerView == null) {
        break missingId;
      }

      id = R.id.text_courses;
      TextView textCourses = rootView.findViewById(id);
      if (textCourses == null) {
        break missingId;
      }

      id = R.id.text_popular_courses;
      TextView textPopularCourses = rootView.findViewById(id);
      if (textPopularCourses == null) {
        break missingId;
      }

      id = R.id.text_popular_trainers;
      TextView textPopularTrainers = rootView.findViewById(id);
      if (textPopularTrainers == null) {
        break missingId;
      }

      id = R.id.text_quizes;
      TextView textQuizes = rootView.findViewById(id);
      if (textQuizes == null) {
        break missingId;
      }

      return new FragmentHomeBinding((NestedScrollView) rootView, coursesRecyclerView,
          popularCoursesRecyclerView, popularTrainersRecyclerView, quizesRecyclerView, textCourses,
          textPopularCourses, textPopularTrainers, textQuizes);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
