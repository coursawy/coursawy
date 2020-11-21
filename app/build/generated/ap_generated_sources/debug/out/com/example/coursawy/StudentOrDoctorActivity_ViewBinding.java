// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudentOrDoctorActivity_ViewBinding implements Unbinder {
  private StudentOrDoctorActivity target;

  @UiThread
  public StudentOrDoctorActivity_ViewBinding(StudentOrDoctorActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudentOrDoctorActivity_ViewBinding(StudentOrDoctorActivity target, View source) {
    this.target = target;

    target.doctor = Utils.findRequiredViewAsType(source, R.id.doctor, "field 'doctor'", TextView.class);
    target.student = Utils.findOptionalViewAsType(source, R.id.student, "field 'student'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudentOrDoctorActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.doctor = null;
    target.student = null;
  }
}
