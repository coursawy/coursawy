package com.example.coursawy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursawy.ui.activities.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.sign_submit_btn)
    Button signSubmitBtn;
    @BindView(R.id.sign_in_layout)
    LinearLayout signInLayout;
    @BindView(R.id.sign_up_layout)
    LinearLayout signUpLayout;
    @BindView(R.id.email_sign_in)
    TextInputEditText emailSignIn;
    @BindView(R.id.password_sign_in)
    TextInputEditText passwordSignIn;
    @BindView(R.id.forgot_password)
    TextView forgotPassword;
    @BindView(R.id.name_sign_up)
    TextInputEditText nameSignUp;
    @BindView(R.id.email_sign_up)
    TextInputEditText emailSignUp;
    @BindView(R.id.text_input_email)
    TextInputLayout textInputEmail;
    @BindView(R.id.text_input_email2)
    TextInputLayout textInputEmail2;
    @BindView(R.id.text_input_password)
    TextInputLayout textInputPassword;
    @BindView(R.id.text_input_conf_password2)
    TextInputLayout textInputConfPassword2;
    @BindView(R.id.text_input_password2)
    TextInputLayout textInputPassword2;
    @BindView(R.id.text_input_name)
    TextInputLayout textInputName;
    @BindView(R.id.password_sign_up)
    TextInputEditText passwordSignUp;
    @BindView(R.id.password_conf_sign_up)
    TextInputEditText passwordConfirmSignUp;
    @BindView(R.id.back_iv)
    ImageView backBtn;


    //declare
    boolean isStudent;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        isStudent = getIntent().getBooleanExtra("isStudent", false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                String s = radioButton.getText().toString();
                if (s.equals("Sign in")) {
                    setTextWithSmoothAnimation(signSubmitBtn, "Sign in");
                    signUpLayout.setVisibility(View.GONE);
                    signInLayout.setVisibility(View.VISIBLE);
                } else {
                    setTextWithSmoothAnimation(signSubmitBtn, "Continue");
                    signUpLayout.setVisibility(View.VISIBLE);
                    signInLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setTextWithSmoothAnimation(Button button, String message) {
        button.animate().setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setText(message);
                button.animate().setListener(null).setDuration(100).alpha(1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).alpha(0);
    }

    private boolean validEmail() {
        String emailText = emailSignUp.getText().toString().trim();
        return emailText.contains("@") && emailText.contains(".");
    }

    private boolean validPassword() {
        String passwordText = passwordSignUp.getText().toString().trim();
        return passwordText.length() >= 6;
    }

    private boolean validatedSignUpForm() {
        String userNameText = nameSignUp.getText().toString().trim();
        String emailText = emailSignUp.getText().toString().trim();
        String passwordText = passwordSignUp.getText().toString().trim();
        String confirmPasswordText = passwordConfirmSignUp.getText().toString().trim();

        if (emailText.isEmpty()) {
            textInputEmail2.setError("required");
            return false;
        } else if (!validEmail()) {
            textInputEmail2.setError("Please Enter Valid Email");
            return false;
        } else {
            textInputEmail2.setError(null);
        }
        if (userNameText.isEmpty()) {
            textInputName.setError("required");
            return false;
        } else {
            textInputName.setError(null);
        }

        if (passwordText.isEmpty()) {
            textInputPassword2.setError("required");
            return false;
        } else if (!validPassword()) {
            textInputPassword2.setError("Please enter more than 6 characters");
            return false;
        } else {
            textInputPassword2.setError(null);
        }
        if (confirmPasswordText.isEmpty()) {
            textInputConfPassword2.setError("required");
            return false;
        } else if (!confirmPasswordText.equals(passwordText)) {
            textInputPassword2.setError("Passwords does not matching");
            textInputConfPassword2.setError("Passwords does not matching");
            return false;
        } else {
            textInputPassword2.setError(null);
            textInputConfPassword2.setError(null);
        }
        return true;
    }

    private boolean validatedSignInForm() {
        String emailText = emailSignIn.getText().toString().trim();
        String passwordText = passwordSignIn.getText().toString().trim();

        if (emailText.isEmpty()) {
            textInputEmail.setError("required");
            return false;
        } else {
            textInputEmail.setError(null);
        }

        if (passwordText.isEmpty()) {
            textInputPassword.setError("required");
            return false;
        } else {
            textInputPassword.setError(null);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_submit_btn) {

            if (signSubmitBtn.getText().equals("Continue") && validatedSignUpForm()) {
                continueSignUp();
            } else if (signSubmitBtn.getText().equals("Sign in") && validatedSignInForm()) {
                logIn();
            } else {
                Toast.makeText(this, "Please Enter valid data", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.back_iv) {
            finish();
        }
    }

    private void continueSignUp() {
        Intent continueSignUp = new Intent(this, ContinueSignupActivity.class);
        continueSignUp.putExtra("userName", nameSignUp.getText().toString());
        continueSignUp.putExtra("email", emailSignUp.getText().toString());
        continueSignUp.putExtra("password", passwordSignUp.getText().toString());
        continueSignUp.putExtra("isStudent", isStudent);
        startActivity(continueSignUp);
    }

    private void logIn() {
//        signSubmitBtn.setEnabled(false);
        auth = FirebaseAuth.getInstance();
        String emailText = emailSignIn.getText().toString().trim();
        String passwordText = passwordSignIn.getText().toString().trim();
        auth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(SignActivity.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                            Intent testIntent=new Intent(SignActivity.this, HomeActivity.class);
                            testIntent.putExtra("isStudentLogin",isStudent);
                            startActivity(testIntent);
                        } else {
//                            signSubmitBtn.setEnabled(true);
                            Toast.makeText(SignActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}