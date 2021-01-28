package com.example.coursawy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswersActivity extends AppCompatActivity {
    @BindView(R.id.a_exam_title_tv)
    TextView titleTv;
    @BindView(R.id.a_mark_tv)
    TextView markTv;
    @BindView(R.id.answers_tv)
    TextView answersTv;

    String title = "";
    String mark = "";
    String answers = "";
    boolean back2exam = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        ButterKnife.bind(this);

        if (getIntent().getExtras().getString("answers") != null) {
            answers = getIntent().getExtras().getString("answers");
        }
        if (getIntent().getExtras().getString("marks") != null) {
            mark = getIntent().getExtras().getString("marks");
        }
        if (getIntent().getExtras().getString("title") != null) {
            title = getIntent().getExtras().getString("title");
        }
        if (getIntent().getExtras().getBoolean("back2exam")) {
            back2exam = getIntent().getExtras().getBoolean("back2exam");
        }
        titleTv.setText(title);
        markTv.setText(mark);
        answersTv.setText(answers);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}