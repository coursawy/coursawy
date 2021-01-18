package com.example.coursawy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.signin_design_btn)
    Button signin_design;
    @BindView(R.id.signup_design_btn)
    Button signup_design;
    @BindView(R.id.signin_btn)
    Button signinBtn;
    @BindView(R.id.sign_continue_btn)
    Button continueBtn;
    @BindView(R.id.signin_layout)
    LinearLayout signInLayout;
    @BindView(R.id.signup_layout)
    LinearLayout signUpLayout;
    @BindView(R.id.email_sign_in)
    TextInputEditText emailSignIn;
    @BindView(R.id.password_sign_in)
    TextInputEditText passwordSignIn;
    @BindView(R.id.forgot_password)
    TextView forgotPassword;
    @BindView(R.id.name_sign_up)
    TextInputEditText nameSignUp;
    @BindView(R.id.fullname_sign_up)
    TextInputEditText fullnameSignUp;
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
    @BindView(R.id.text_input_fullname)
    TextInputLayout textInputFullName;
    @BindView(R.id.password_sign_up)
    TextInputEditText passwordSignUp;
    @BindView(R.id.password_conf_sign_up)
    TextInputEditText passwordConfirmSignUp;
    @BindView(R.id.sign_back_btn)
    ImageView backBtn;


    private boolean isStudent;
    private FirebaseAuth auth;
    private boolean emailAddressChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        isStudent = getIntent().getBooleanExtra("isStudent", false);
        auth = FirebaseAuth.getInstance();


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
        String userFullNameText = fullnameSignUp.getText().toString().trim();
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
        if (userFullNameText.isEmpty()) {
            textInputFullName.setError("required");
            return false;
        } else {
            textInputFullName.setError(null);
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

    private void verifyEmailAddress(){
        FirebaseUser user=auth.getCurrentUser();
        emailAddressChecker=user.isEmailVerified();

        if (emailAddressChecker){
            sendUserToExamActivity();
        }
        else {
            Toast.makeText(this, "Please,verify your account first.", Toast.LENGTH_SHORT).show();
            auth.signOut();
        }
    }

    private void sendUserToExamActivity() {
        Intent testIntent=new Intent(SignActivity.this, ExamActivity.class);
        testIntent.putExtra("isStudentLogin",isStudent);
        startActivity(testIntent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_back_btn) {
            finish();
        }
        else if (view.getId()==R.id.signin_design_btn){
            signUpLayout.setVisibility(View.GONE);
            continueBtn.setVisibility(View.GONE);
            signInLayout.setVisibility(View.VISIBLE);
            signinBtn.setVisibility(View.VISIBLE);
            signin_design.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorParper)));
            signin_design.setTextColor(getResources().getColor(android.R.color.white));
            signup_design.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            signup_design.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }
        else if (view.getId()==R.id.signup_design_btn){
            signInLayout.setVisibility(View.GONE);
            signinBtn.setVisibility(View.GONE);
            signUpLayout.setVisibility(View.VISIBLE);
            continueBtn.setVisibility(View.VISIBLE);
            signup_design.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorParper)));
            signup_design.setTextColor(getResources().getColor(android.R.color.white));
            signin_design.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
            signin_design.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }
        else if (view.getId()==R.id.signin_btn){
            if (validatedSignInForm()){
                logIn();
            }
        }
        else if (view.getId()==R.id.sign_continue_btn){
            if (validatedSignUpForm()){
                continueSignUp();
            }
        }
    }

    private void continueSignUp() {
        Intent continueSignUp = new Intent(this, ContinueSignupActivity.class);
        continueSignUp.putExtra("userName", nameSignUp.getText().toString());
        continueSignUp.putExtra("fullName", fullnameSignUp.getText().toString());
        continueSignUp.putExtra("email", emailSignUp.getText().toString());
        continueSignUp.putExtra("password", passwordSignUp.getText().toString());
        continueSignUp.putExtra("isStudent", isStudent);
        startActivity(continueSignUp);
    }

    private void logIn() {

        String emailText = emailSignIn.getText().toString().trim();
        String passwordText = passwordSignIn.getText().toString().trim();
        auth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            verifyEmailAddress();
                        } else {
                            Toast.makeText(SignActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}