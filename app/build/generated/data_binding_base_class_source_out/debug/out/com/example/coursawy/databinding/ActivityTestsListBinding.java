// Generated by view binder compiler. Do not edit!
package com.example.coursawy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.coursawy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTestsListBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ProgressBar progress;

  @NonNull
  public final TextView testAlertTv;

  @NonNull
  public final ListView testsListView;

  private ActivityTestsListBinding(@NonNull RelativeLayout rootView, @NonNull ProgressBar progress,
      @NonNull TextView testAlertTv, @NonNull ListView testsListView) {
    this.rootView = rootView;
    this.progress = progress;
    this.testAlertTv = testAlertTv;
    this.testsListView = testsListView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTestsListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTestsListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_tests_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTestsListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.progress;
      ProgressBar progress = rootView.findViewById(id);
      if (progress == null) {
        break missingId;
      }

      id = R.id.test_alert_tv;
      TextView testAlertTv = rootView.findViewById(id);
      if (testAlertTv == null) {
        break missingId;
      }

      id = R.id.tests_list_view;
      ListView testsListView = rootView.findViewById(id);
      if (testsListView == null) {
        break missingId;
      }

      return new ActivityTestsListBinding((RelativeLayout) rootView, progress, testAlertTv,
          testsListView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}