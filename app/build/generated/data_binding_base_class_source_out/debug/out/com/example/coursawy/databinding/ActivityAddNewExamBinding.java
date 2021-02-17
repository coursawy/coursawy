// Generated by view binder compiler. Do not edit!
package com.example.coursawy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.coursawy.R;
import com.google.android.material.textfield.TextInputEditText;
import in.aabhasjindal.otptextview.OtpTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddNewExamBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout addNewExamLayout;

  @NonNull
  public final LinearLayout appBarMain;

  @NonNull
  public final ImageView backIv;

  @NonNull
  public final OtpTextView confirmExamCode;

  @NonNull
  public final CardView courseQuizBtn;

  @NonNull
  public final ConstraintLayout courseQuizLayout;

  @NonNull
  public final Spinner coursesQuizSpinner;

  @NonNull
  public final EditText descriptionEt;

  @NonNull
  public final LinearLayout downloadBtn;

  @NonNull
  public final OtpTextView examCode;

  @NonNull
  public final ConstraintLayout examInstructionsLayout;

  @NonNull
  public final TextView examTitleTv;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final Button okBtn;

  @NonNull
  public final RadioButton privateBtn;

  @NonNull
  public final RadioGroup privatePublicBtnView;

  @NonNull
  public final RadioButton publicBtn;

  @NonNull
  public final TextInputEditText quizNameEt;

  @NonNull
  public final CardView secureExamBtn;

  @NonNull
  public final Spinner secureExamCoursesSpinner;

  @NonNull
  public final ConstraintLayout secureExamLayout;

  @NonNull
  public final AppCompatImageView star;

  @NonNull
  public final ImageView star2;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView uploadExamTv;

  @NonNull
  public final LinearLayout uploadQuizBtn;

  @NonNull
  public final View view;

  private ActivityAddNewExamBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout addNewExamLayout, @NonNull LinearLayout appBarMain,
      @NonNull ImageView backIv, @NonNull OtpTextView confirmExamCode,
      @NonNull CardView courseQuizBtn, @NonNull ConstraintLayout courseQuizLayout,
      @NonNull Spinner coursesQuizSpinner, @NonNull EditText descriptionEt,
      @NonNull LinearLayout downloadBtn, @NonNull OtpTextView examCode,
      @NonNull ConstraintLayout examInstructionsLayout, @NonNull TextView examTitleTv,
      @NonNull ImageView imageView, @NonNull Button okBtn, @NonNull RadioButton privateBtn,
      @NonNull RadioGroup privatePublicBtnView, @NonNull RadioButton publicBtn,
      @NonNull TextInputEditText quizNameEt, @NonNull CardView secureExamBtn,
      @NonNull Spinner secureExamCoursesSpinner, @NonNull ConstraintLayout secureExamLayout,
      @NonNull AppCompatImageView star, @NonNull ImageView star2, @NonNull TextView textView2,
      @NonNull TextView textView3, @NonNull TextView textView5, @NonNull TextView uploadExamTv,
      @NonNull LinearLayout uploadQuizBtn, @NonNull View view) {
    this.rootView = rootView;
    this.addNewExamLayout = addNewExamLayout;
    this.appBarMain = appBarMain;
    this.backIv = backIv;
    this.confirmExamCode = confirmExamCode;
    this.courseQuizBtn = courseQuizBtn;
    this.courseQuizLayout = courseQuizLayout;
    this.coursesQuizSpinner = coursesQuizSpinner;
    this.descriptionEt = descriptionEt;
    this.downloadBtn = downloadBtn;
    this.examCode = examCode;
    this.examInstructionsLayout = examInstructionsLayout;
    this.examTitleTv = examTitleTv;
    this.imageView = imageView;
    this.okBtn = okBtn;
    this.privateBtn = privateBtn;
    this.privatePublicBtnView = privatePublicBtnView;
    this.publicBtn = publicBtn;
    this.quizNameEt = quizNameEt;
    this.secureExamBtn = secureExamBtn;
    this.secureExamCoursesSpinner = secureExamCoursesSpinner;
    this.secureExamLayout = secureExamLayout;
    this.star = star;
    this.star2 = star2;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView5 = textView5;
    this.uploadExamTv = uploadExamTv;
    this.uploadQuizBtn = uploadQuizBtn;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddNewExamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddNewExamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_new_exam, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddNewExamBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_new_exam_layout;
      ConstraintLayout addNewExamLayout = rootView.findViewById(id);
      if (addNewExamLayout == null) {
        break missingId;
      }

      id = R.id.app_bar_main;
      LinearLayout appBarMain = rootView.findViewById(id);
      if (appBarMain == null) {
        break missingId;
      }

      id = R.id.back_iv;
      ImageView backIv = rootView.findViewById(id);
      if (backIv == null) {
        break missingId;
      }

      id = R.id.confirm_exam_code;
      OtpTextView confirmExamCode = rootView.findViewById(id);
      if (confirmExamCode == null) {
        break missingId;
      }

      id = R.id.course_quiz_btn;
      CardView courseQuizBtn = rootView.findViewById(id);
      if (courseQuizBtn == null) {
        break missingId;
      }

      id = R.id.course_quiz_layout;
      ConstraintLayout courseQuizLayout = rootView.findViewById(id);
      if (courseQuizLayout == null) {
        break missingId;
      }

      id = R.id.courses_quiz_spinner;
      Spinner coursesQuizSpinner = rootView.findViewById(id);
      if (coursesQuizSpinner == null) {
        break missingId;
      }

      id = R.id.description_et;
      EditText descriptionEt = rootView.findViewById(id);
      if (descriptionEt == null) {
        break missingId;
      }

      id = R.id.download_btn;
      LinearLayout downloadBtn = rootView.findViewById(id);
      if (downloadBtn == null) {
        break missingId;
      }

      id = R.id.exam_code;
      OtpTextView examCode = rootView.findViewById(id);
      if (examCode == null) {
        break missingId;
      }

      id = R.id.exam_instructions_layout;
      ConstraintLayout examInstructionsLayout = rootView.findViewById(id);
      if (examInstructionsLayout == null) {
        break missingId;
      }

      id = R.id.exam_title_tv;
      TextView examTitleTv = rootView.findViewById(id);
      if (examTitleTv == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = rootView.findViewById(id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.ok_btn;
      Button okBtn = rootView.findViewById(id);
      if (okBtn == null) {
        break missingId;
      }

      id = R.id.private_btn;
      RadioButton privateBtn = rootView.findViewById(id);
      if (privateBtn == null) {
        break missingId;
      }

      id = R.id.private_public_btn_view;
      RadioGroup privatePublicBtnView = rootView.findViewById(id);
      if (privatePublicBtnView == null) {
        break missingId;
      }

      id = R.id.public_btn;
      RadioButton publicBtn = rootView.findViewById(id);
      if (publicBtn == null) {
        break missingId;
      }

      id = R.id.quiz_name_et;
      TextInputEditText quizNameEt = rootView.findViewById(id);
      if (quizNameEt == null) {
        break missingId;
      }

      id = R.id.secure_exam_btn;
      CardView secureExamBtn = rootView.findViewById(id);
      if (secureExamBtn == null) {
        break missingId;
      }

      id = R.id.secure_exam_courses_spinner;
      Spinner secureExamCoursesSpinner = rootView.findViewById(id);
      if (secureExamCoursesSpinner == null) {
        break missingId;
      }

      id = R.id.secure_exam_layout;
      ConstraintLayout secureExamLayout = rootView.findViewById(id);
      if (secureExamLayout == null) {
        break missingId;
      }

      id = R.id.star;
      AppCompatImageView star = rootView.findViewById(id);
      if (star == null) {
        break missingId;
      }

      id = R.id.star2;
      ImageView star2 = rootView.findViewById(id);
      if (star2 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = rootView.findViewById(id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.upload_exam_tv;
      TextView uploadExamTv = rootView.findViewById(id);
      if (uploadExamTv == null) {
        break missingId;
      }

      id = R.id.upload_quiz_btn;
      LinearLayout uploadQuizBtn = rootView.findViewById(id);
      if (uploadQuizBtn == null) {
        break missingId;
      }

      id = R.id.view;
      View view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      return new ActivityAddNewExamBinding((ConstraintLayout) rootView, addNewExamLayout,
          appBarMain, backIv, confirmExamCode, courseQuizBtn, courseQuizLayout, coursesQuizSpinner,
          descriptionEt, downloadBtn, examCode, examInstructionsLayout, examTitleTv, imageView,
          okBtn, privateBtn, privatePublicBtnView, publicBtn, quizNameEt, secureExamBtn,
          secureExamCoursesSpinner, secureExamLayout, star, star2, textView2, textView3, textView5,
          uploadExamTv, uploadQuizBtn, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}