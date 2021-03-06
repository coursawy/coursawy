// Generated by view binder compiler. Do not edit!
package com.example.coursawy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.coursawy.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NotificationItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView notifyCourseName;

  @NonNull
  public final CircleImageView notifyDoctorImage;

  @NonNull
  public final TextView notifyDoctorName;

  @NonNull
  public final TextView notifyTime;

  private NotificationItemBinding(@NonNull CardView rootView, @NonNull TextView notifyCourseName,
      @NonNull CircleImageView notifyDoctorImage, @NonNull TextView notifyDoctorName,
      @NonNull TextView notifyTime) {
    this.rootView = rootView;
    this.notifyCourseName = notifyCourseName;
    this.notifyDoctorImage = notifyDoctorImage;
    this.notifyDoctorName = notifyDoctorName;
    this.notifyTime = notifyTime;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static NotificationItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NotificationItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.notification_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NotificationItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.notify_course_name;
      TextView notifyCourseName = rootView.findViewById(id);
      if (notifyCourseName == null) {
        break missingId;
      }

      id = R.id.notify_doctor_image;
      CircleImageView notifyDoctorImage = rootView.findViewById(id);
      if (notifyDoctorImage == null) {
        break missingId;
      }

      id = R.id.notify_doctor_name;
      TextView notifyDoctorName = rootView.findViewById(id);
      if (notifyDoctorName == null) {
        break missingId;
      }

      id = R.id.notify_time;
      TextView notifyTime = rootView.findViewById(id);
      if (notifyTime == null) {
        break missingId;
      }

      return new NotificationItemBinding((CardView) rootView, notifyCourseName, notifyDoctorImage,
          notifyDoctorName, notifyTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
