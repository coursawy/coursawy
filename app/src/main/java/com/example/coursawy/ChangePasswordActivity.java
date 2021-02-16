package com.example.coursawy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    ImageView backIv;
    TextInputLayout textInputNewPassword;
    TextInputEditText newPasswordEt;
    TextInputLayout textInputConfPassword;
    TextInputEditText confPasswordEt;
    Button submitButton;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        backIv = findViewById(R.id.back_iv);
        newPasswordEt = findViewById(R.id.new_password);
        confPasswordEt = findViewById(R.id.conf_password);
        textInputNewPassword = findViewById(R.id.text_input_new_password);
        textInputConfPassword = findViewById(R.id.text_input_conf_password);
        submitButton = findViewById(R.id.submit_btn);
        user = FirebaseAuth.getInstance().getCurrentUser();
        backIv.setOnClickListener(v -> onBackPressed());
        submitButton.setOnClickListener(v -> {
            String newPass = textInputNewPassword.getEditText().getText().toString().trim();
            String confPass = textInputConfPassword.getEditText().getText().toString().trim();
            if (newPass.isEmpty()) {
                textInputNewPassword.setError("This field can't be empty!");
                return;
            } else {
                textInputNewPassword.setError(null);
            }
            if (confPass.isEmpty()) {
                textInputConfPassword.setError("This field can't be empty!");
                return;
            } else {
                textInputNewPassword.setError(null);
            }
            if (!newPass.equals(confPass)) {
                textInputConfPassword.setError("Not the same password!");
            } else {
                textInputNewPassword.setError(null);
                user.updatePassword(newPass).addOnCompleteListener(task -> {
                    Toast.makeText(ChangePasswordActivity.this, "Your password has been changed successfully!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                });
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}