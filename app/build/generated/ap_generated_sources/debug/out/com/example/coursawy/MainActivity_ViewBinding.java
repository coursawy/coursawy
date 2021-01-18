// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.doctor = Utils.findRequiredViewAsType(source, R.id.main_doctor_cardview, "field 'doctor'", CardView.class);
    target.student = Utils.findOptionalViewAsType(source, R.id.main_student_cardview, "field 'student'", CardView.class);
    target.continuebtn = Utils.findRequiredViewAsType(source, R.id.main_continue_btn, "field 'continuebtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.doctor = null;
    target.student = null;
    target.continuebtn = null;
  }
}
