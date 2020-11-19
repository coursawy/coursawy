// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignActivity_ViewBinding implements Unbinder {
  private SignActivity target;

  @UiThread
  public SignActivity_ViewBinding(SignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignActivity_ViewBinding(SignActivity target, View source) {
    this.target = target;

    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radio_group, "field 'radioGroup'", RadioGroup.class);
    target.signSubmitBtn = Utils.findRequiredViewAsType(source, R.id.sign_submit_btn, "field 'signSubmitBtn'", Button.class);
    target.signInLayout = Utils.findRequiredViewAsType(source, R.id.sign_in_layout, "field 'signInLayout'", LinearLayout.class);
    target.signUpLayout = Utils.findRequiredViewAsType(source, R.id.sign_up_layout, "field 'signUpLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioGroup = null;
    target.signSubmitBtn = null;
    target.signInLayout = null;
    target.signUpLayout = null;
  }
}
