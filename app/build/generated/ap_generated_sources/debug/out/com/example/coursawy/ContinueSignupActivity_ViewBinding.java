// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContinueSignupActivity_ViewBinding implements Unbinder {
  private ContinueSignupActivity target;

  @UiThread
  public ContinueSignupActivity_ViewBinding(ContinueSignupActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContinueSignupActivity_ViewBinding(ContinueSignupActivity target, View source) {
    this.target = target;

    target.mDisplayDate = Utils.findRequiredViewAsType(source, R.id.Date, "field 'mDisplayDate'", TextView.class);
    target.spinner_faculty = Utils.findRequiredViewAsType(source, R.id.spinner_faculty, "field 'spinner_faculty'", Spinner.class);
    target.student_spinner_grade = Utils.findRequiredViewAsType(source, R.id.student_spinner_grade, "field 'student_spinner_grade'", Spinner.class);
    target.doctor_spinner_grade = Utils.findRequiredViewAsType(source, R.id.doctor_spinner_grade, "field 'doctor_spinner_grade'", Spinner.class);
    target.departmentLabel = Utils.findRequiredViewAsType(source, R.id.department_lable, "field 'departmentLabel'", TextView.class);
    target.codeLabel = Utils.findRequiredViewAsType(source, R.id.code_lable, "field 'codeLabel'", TextView.class);
    target.department = Utils.findRequiredViewAsType(source, R.id.continue_department, "field 'department'", EditText.class);
    target.code = Utils.findRequiredViewAsType(source, R.id.continue_code, "field 'code'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.continue_profile_phone, "field 'phone'", EditText.class);
    target.signUpBtn = Utils.findRequiredViewAsType(source, R.id.signin_btn, "field 'signUpBtn'", Button.class);
    target.profImage = Utils.findRequiredViewAsType(source, R.id.continue_profile_pic, "field 'profImage'", CircleImageView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContinueSignupActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDisplayDate = null;
    target.spinner_faculty = null;
    target.student_spinner_grade = null;
    target.doctor_spinner_grade = null;
    target.departmentLabel = null;
    target.codeLabel = null;
    target.department = null;
    target.code = null;
    target.phone = null;
    target.signUpBtn = null;
    target.profImage = null;
    target.progressBar = null;
  }
}
