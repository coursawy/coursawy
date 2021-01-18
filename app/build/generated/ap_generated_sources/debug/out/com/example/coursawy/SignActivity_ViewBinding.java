// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

    target.signin_design = Utils.findRequiredViewAsType(source, R.id.signin_design_btn, "field 'signin_design'", Button.class);
    target.signup_design = Utils.findRequiredViewAsType(source, R.id.signup_design_btn, "field 'signup_design'", Button.class);
    target.signinBtn = Utils.findRequiredViewAsType(source, R.id.signin_btn, "field 'signinBtn'", Button.class);
    target.continueBtn = Utils.findRequiredViewAsType(source, R.id.sign_continue_btn, "field 'continueBtn'", Button.class);
    target.signInLayout = Utils.findRequiredViewAsType(source, R.id.signin_layout, "field 'signInLayout'", LinearLayout.class);
    target.signUpLayout = Utils.findRequiredViewAsType(source, R.id.signup_layout, "field 'signUpLayout'", LinearLayout.class);
    target.emailSignIn = Utils.findRequiredViewAsType(source, R.id.email_sign_in, "field 'emailSignIn'", TextInputEditText.class);
    target.passwordSignIn = Utils.findRequiredViewAsType(source, R.id.password_sign_in, "field 'passwordSignIn'", TextInputEditText.class);
    target.forgotPassword = Utils.findRequiredViewAsType(source, R.id.forgot_password, "field 'forgotPassword'", TextView.class);
    target.nameSignUp = Utils.findRequiredViewAsType(source, R.id.name_sign_up, "field 'nameSignUp'", TextInputEditText.class);
    target.fullnameSignUp = Utils.findRequiredViewAsType(source, R.id.fullname_sign_up, "field 'fullnameSignUp'", TextInputEditText.class);
    target.emailSignUp = Utils.findRequiredViewAsType(source, R.id.email_sign_up, "field 'emailSignUp'", TextInputEditText.class);
    target.textInputEmail = Utils.findRequiredViewAsType(source, R.id.text_input_email, "field 'textInputEmail'", TextInputLayout.class);
    target.textInputEmail2 = Utils.findRequiredViewAsType(source, R.id.text_input_email2, "field 'textInputEmail2'", TextInputLayout.class);
    target.textInputPassword = Utils.findRequiredViewAsType(source, R.id.text_input_password, "field 'textInputPassword'", TextInputLayout.class);
    target.textInputConfPassword2 = Utils.findRequiredViewAsType(source, R.id.text_input_conf_password2, "field 'textInputConfPassword2'", TextInputLayout.class);
    target.textInputPassword2 = Utils.findRequiredViewAsType(source, R.id.text_input_password2, "field 'textInputPassword2'", TextInputLayout.class);
    target.textInputName = Utils.findRequiredViewAsType(source, R.id.text_input_name, "field 'textInputName'", TextInputLayout.class);
    target.textInputFullName = Utils.findRequiredViewAsType(source, R.id.text_input_fullname, "field 'textInputFullName'", TextInputLayout.class);
    target.passwordSignUp = Utils.findRequiredViewAsType(source, R.id.password_sign_up, "field 'passwordSignUp'", TextInputEditText.class);
    target.passwordConfirmSignUp = Utils.findRequiredViewAsType(source, R.id.password_conf_sign_up, "field 'passwordConfirmSignUp'", TextInputEditText.class);
    target.backBtn = Utils.findRequiredViewAsType(source, R.id.sign_back_btn, "field 'backBtn'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.signin_design = null;
    target.signup_design = null;
    target.signinBtn = null;
    target.continueBtn = null;
    target.signInLayout = null;
    target.signUpLayout = null;
    target.emailSignIn = null;
    target.passwordSignIn = null;
    target.forgotPassword = null;
    target.nameSignUp = null;
    target.fullnameSignUp = null;
    target.emailSignUp = null;
    target.textInputEmail = null;
    target.textInputEmail2 = null;
    target.textInputPassword = null;
    target.textInputConfPassword2 = null;
    target.textInputPassword2 = null;
    target.textInputName = null;
    target.textInputFullName = null;
    target.passwordSignUp = null;
    target.passwordConfirmSignUp = null;
    target.backBtn = null;
  }
}
