// Generated by view binder compiler. Do not edit!
package com.example.coursawy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import com.example.coursawy.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatImageView backIv;

  @NonNull
  public final TextView changePassTv;

  @NonNull
  public final TextView editProfileTv;

  @NonNull
  public final TextView settingsTv;

  private ActivitySettingsBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatImageView backIv, @NonNull TextView changePassTv,
      @NonNull TextView editProfileTv, @NonNull TextView settingsTv) {
    this.rootView = rootView;
    this.backIv = backIv;
    this.changePassTv = changePassTv;
    this.editProfileTv = editProfileTv;
    this.settingsTv = settingsTv;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_iv;
      AppCompatImageView backIv = rootView.findViewById(id);
      if (backIv == null) {
        break missingId;
      }

      id = R.id.change_pass_tv;
      TextView changePassTv = rootView.findViewById(id);
      if (changePassTv == null) {
        break missingId;
      }

      id = R.id.edit_profile_tv;
      TextView editProfileTv = rootView.findViewById(id);
      if (editProfileTv == null) {
        break missingId;
      }

      id = R.id.settings_tv;
      TextView settingsTv = rootView.findViewById(id);
      if (settingsTv == null) {
        break missingId;
      }

      return new ActivitySettingsBinding((RelativeLayout) rootView, backIv, changePassTv,
          editProfileTv, settingsTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
