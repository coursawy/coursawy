package com.example.coursawy;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignActivity extends AppCompatActivity {
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.sign_submit_btn)
    Button signSubmitBtn;
    @BindView(R.id.sign_in_layout)
    LinearLayout signInLayout;
    @BindView(R.id.sign_up_layout)
    LinearLayout signUpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
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

}