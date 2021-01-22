package com.example.coursawy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    @BindView(R.id.sign_gmail_btn)
    ImageView gmailBtn;
    @BindView(R.id.sign_facbook_btn)
    LoginButton facebookBtn;

    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN= 123;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;

    private boolean isStudent;
    private FirebaseAuth auth;
    private boolean emailAddressChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        facebookBtn.setBackgroundResource(R.drawable.facebook);
        facebookBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        facebookBtn.setCompoundDrawablePadding(0);
        facebookBtn.setPadding(10, 50, 10, 50);
        facebookBtn.setText("");
        facebookBtn.setLoginText("");
        isStudent = getIntent().getBooleanExtra("isStudent", false);
        auth = FirebaseAuth.getInstance();
        createRequest();
        FacebookSdk.sdkInitialize(SignActivity.this);
        callbackManager = CallbackManager.Factory.create();
        facebookBtn.setReadPermissions("email", "public_profile");
        facebookBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(SignActivity.this, "successful", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(SignActivity.this, "User cancelled it", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        getHashkey();
//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                if (currentAccessToken == null) {
//                    auth.signOut();
//                }
//            }
//        };

    }


    public void getHashkey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.i("Base64", Base64.encodeToString(md.digest(), Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Name not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
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

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }


                });
    }
    AccessTokenTracker accessTokenTracker2 = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                LoginManager.getInstance().logOut();

            }
        }
    };


    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "congratulations", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SignActivity.this,ContinueSignupActivity.class);
            startActivity(i);
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_back_btn) {
            finish();
        }
        if (view.getId() == R.id.sign_gmail_btn) {
            signIn();
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


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(SignActivity.this,ExamActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker2.stopTracking();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("google", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("google", "Google sign in failed", e);
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                // ..
            }
        }
    }
}