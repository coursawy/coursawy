// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
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
    target.emailSignIn = Utils.findRequiredViewAsType(source, R.id.email_sign_in, "field 'emailSignIn'", TextInputEditText.class);
    target.passwordSignIn = Utils.findRequiredViewAsType(source, R.id.password_sign_in, "field 'passwordSignIn'", TextInputEditText.class);
    target.forgotPassword = Utils.findRequiredViewAsType(source, R.id.forgot_password, "field 'forgotPassword'", TextView.class);
    target.nameSignUp = Utils.findRequiredViewAsType(source, R.id.name_sign_up, "field 'nameSignUp'", TextInputEditText.class);
    target.emailSignUp = Utils.findRequiredViewAsType(source, R.id.email_sign_up, "field 'emailSignUp'", TextInputEditText.class);
    target.passwordSignUp = Utils.findRequiredViewAsType(source, R.id.password_sign_up, "field 'passwordSignUp'", TextInputEditText.class);
    target.passwordConfirmSignUp = Utils.findRequiredViewAsType(source, R.id.password_conf_sign_up, "field 'passwordConfirmSignUp'", TextInputEditText.class);
    target.backBtn = Utils.findRequiredViewAsType(source, R.id.back_iv, "field 'backBtn'", ImageView.class);
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
    target.emailSignIn = null;
    target.passwordSignIn = null;
    target.forgotPassword = null;
    target.nameSignUp = null;
    target.emailSignUp = null;
    target.passwordSignUp = null;
    target.passwordConfirmSignUp = null;
    target.backBtn = null;
  }
}
