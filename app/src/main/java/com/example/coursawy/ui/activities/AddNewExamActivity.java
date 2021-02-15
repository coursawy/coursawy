package com.example.coursawy.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.coursawy.R;
import com.example.coursawy.databinding.ActivityAddNewExamBinding;
import com.example.coursawy.model.Exam;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class AddNewExamActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAddNewExamBinding binding;
    private String exam, selectedCourse, quizTitle, description;
    private String[] coursesList;
    private String[] coursesSecureExamList;
    private int position;
    private int positionOfSecureExamList;
    private String selectedSecureExamCourse;
    private int selectedPrivacyId;
    private String examPrivacy;
    private String currentUserID;
    private DatabaseReference userRef;
    private RadioButton radioButton;



    List<String> questions;
    List<String> answers1;
    List<String> answers2;
    List<String> answers3;
    List<String> answers4;
    List<String> correctAnswers;
    FirebaseDatabase database;
    private List<Exam> allQuestionsList = new ArrayList<>();
    private String examCode , confirmExamCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.examInstructionsLayout.setVisibility(View.VISIBLE);
        disableAndEnableViews(false);
        createCoursesQuizSpinner();
        createCoursesSecureExamSpinner();
        exam = "CourseQuiz";
        examPrivacy = "private";

        binding.privatePublicBtnView.setOnCheckedChangeListener((radioGroup, id) -> {
            if (examPrivacy.equals("CourseQuiz")) {
                switch (id) {
                    case R.id.private_btn:
                        examPrivacy = "private";
                    case R.id.public_btn:
                        examPrivacy = "public";
                }
            }
        });
    }


    private void createCoursesQuizSpinner() {
        coursesList = getResources().getStringArray(R.array.Courses);
        binding.coursesQuizSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                position = pos;
                selectedCourse = coursesList[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createCoursesSecureExamSpinner() {
        coursesSecureExamList = getResources().getStringArray(R.array.Courses);
        binding.secureExamCoursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                positionOfSecureExamList = pos;
                selectedSecureExamCourse = coursesSecureExamList[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        CardView courseQuizCardView = binding.courseQuizBtn;
        CardView secureExamCardView = binding.secureExamBtn;
        if (id == R.id.course_quiz_btn) {
            exam = "CourseQuiz";
            courseQuizCardView.setUseCompatPadding(true);
            secureExamCardView.setUseCompatPadding(false);
            courseQuizCardView.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.selected_btn_color)));
            secureExamCardView.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.not_selected_btn_color)));
            binding.secureExamLayout.setVisibility(View.GONE);
            binding.courseQuizLayout.setVisibility(View.VISIBLE);
            binding.uploadExamTv.setText("upload quiz");
        } else if (id == R.id.secure_exam_btn) {
            exam = "SecureQuiz";
            secureExamCardView.setUseCompatPadding(true);
            courseQuizCardView.setUseCompatPadding(false);
            secureExamCardView.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.selected_btn_color)));
            courseQuizCardView.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.not_selected_btn_color)));
            binding.courseQuizLayout.setVisibility(View.GONE);
            binding.secureExamLayout.setVisibility(View.VISIBLE);
            binding.uploadExamTv.setText("upload exam");
        } else if (id == R.id.upload_quiz_btn) {
            uploadExamFromLocalStorage();
        } else if (id == R.id.download_btn) {
            download();
        } else if (id == R.id.ok_btn) {
            binding.examInstructionsLayout.setVisibility(View.GONE);
            disableAndEnableViews(true);
        } else if (id == R.id.back_iv) {
            finish();
        }


    }

    private void uploadExamFromLocalStorage() {
        if (exam.equals("CourseQuiz")){
            if (validatedCourseQuizData()){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                startActivityForResult(intent.createChooser(intent, "Select Excel Files"), 430);
            }
        }else if (exam.equals("SecureQuiz")) {
            if (validatedSecureExamData()){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                startActivityForResult(intent.createChooser(intent, "Select Excel Files"), 430);
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 430 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            readExcelFile(data.getData());
        }
    }

    private void readExcelFile(Uri filePath) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(filePath);
            XSSFWorkbook xssfSheets = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfSheets.getSheetAt(0);
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            questions = new ArrayList<>();
            answers1 = new ArrayList<>();
            answers2 = new ArrayList<>();
            answers3 = new ArrayList<>();
            answers4 = new ArrayList<>();
            correctAnswers = new ArrayList<>();
            for (Row row : sheet) {
                for (int i = 0; i < row.getHeight(); i++) {
                    String cellValue = dataFormatter.formatCellValue(row.getCell(i));
                    switch (i) {
                        case 0:
                            questions.add(cellValue);
                            break;
                        case 1:
                            answers1.add(cellValue);
                            break;
                        case 2:
                            answers2.add(cellValue);
                            break;
                        case 3:
                            answers3.add(cellValue);
                            break;
                        case 4:
                            answers4.add(cellValue);
                            break;
                        case 5:
                            correctAnswers.add(cellValue);
                            break;
                    }
                }
            }
            addExamToFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addExamToFirebase() {
        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference();
        String examId = userRef.push().getKey();
        if (exam.equals("CourseQuiz")) {
            userRef = userRef.child("Users").child(currentUserID).child("Exams")
                    .child(exam).child(examId);
            HashMap<String, Object> courseQuiz = new HashMap<>();
            courseQuiz.put("exam_type", exam);
            courseQuiz.put("course_name", selectedCourse);
            courseQuiz.put("quiz_title", quizTitle);
            courseQuiz.put("description", description);
            courseQuiz.put("privacy", examPrivacy);
            userRef.updateChildren(courseQuiz).addOnCompleteListener(runnable -> {
                Toast.makeText(this, "Quiz Added Successfully!", Toast.LENGTH_SHORT).show();
                resetViews();
            });


        }else if (exam.equals("SecureQuiz")){
            userRef = userRef.child("Users").child(currentUserID).child("Exams")
                    .child(exam).child(examId);

            HashMap<String, Object> secureExam = new HashMap<>();
            secureExam.put("exam_type", exam);
            secureExam.put("course_name", selectedSecureExamCourse);
            secureExam.put("exam_code", examCode);

            userRef.updateChildren(secureExam).addOnCompleteListener(runnable -> {
                Toast.makeText(this, "Exam Added Successfully!", Toast.LENGTH_SHORT).show();
                resetViews();
            });
        }


//        DatabaseReference myRef = database.getReference("Test Exam");
//        userRef.removeValue();

        for (int i = 0; i < questions.size(); i++) {
            String questionId = userRef.push().getKey();
            String q = questions.get(i).trim(),
                    a1 = answers1.get(i).trim(),
                    a2 = answers2.get(i).trim(),
                    a3 = answers3.get(i).trim(),
                    a4 = answers4.get(i).trim(),
                    ra = correctAnswers.get(i).trim();
            Exam exam = new Exam(questionId, q, a1, a2, a3, a4, ra);
//            myRef.child(id).setValue(exam);

            userRef.child(questionId)
                    .setValue(exam);
        }

//        Intent i = new Intent(this, TestsActivity.class);
//        i.putExtra("type","online");
//        i.putExtra("unitName","Test Exam");
//        i.putExtra("id","Test Exam");
//        startActivity(i);
    }

    private void resetViews() {
        binding.quizNameEt.setText(null);
        binding.descriptionEt.setText(null);
        selectedPrivacyId = binding.privatePublicBtnView.getCheckedRadioButtonId();
        radioButton = findViewById(selectedPrivacyId);
        examPrivacy = radioButton.getText().toString();
        selectedCourse = coursesList[position];

        binding.examCode.setOTP(null);
        binding.confirmExamCode.setOTP(null);
    }

    private boolean validatedCourseQuizData() {
        selectedCourse = (String) binding.coursesQuizSpinner.getItemAtPosition(position);
        quizTitle = binding.quizNameEt.getText().toString();
        description = binding.descriptionEt.getText().toString();
        if (selectedCourse.equals(coursesList[0])) {
            binding.coursesQuizSpinner.requestFocus();
            Toast.makeText(this, "Please select your course", Toast.LENGTH_SHORT).show();
            return false;
        } else if (quizTitle.isEmpty()) {
            binding.quizNameEt.setError("required");
            Toast.makeText(this, "Please enter quiz title", Toast.LENGTH_SHORT).show();
            return false;
        } else if (description.isEmpty()) {
            binding.descriptionEt.setError("required");
            Toast.makeText(this, "Please enter quiz description", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validatedSecureExamData() {
        selectedSecureExamCourse = (String) binding.secureExamCoursesSpinner
                .getItemAtPosition(positionOfSecureExamList);
        examCode = binding.examCode.getOTP();
        confirmExamCode = binding.confirmExamCode.getOTP();

        if (selectedSecureExamCourse.equals(coursesSecureExamList[0])){
            binding.secureExamCoursesSpinner.requestFocus();
            Toast.makeText(this, "Please select your course", Toast.LENGTH_SHORT).show();
            return false;
        }if (examCode.isEmpty()){
            binding.examCode.showError();
            Toast.makeText(this, "Please enter exam code", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!validPassword()) {
            binding.examCode.showError();
            Toast.makeText(this, "Please enter confirm exam code", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (confirmExamCode.isEmpty()) {
            binding.confirmExamCode.showError();
            return false;
        } else if (!confirmExamCode.equals(examCode)) {
            binding.examCode.showError();
            binding.confirmExamCode.showError();
            Toast.makeText(this, "Codes does not matching", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validPassword() {
        String examCode = binding.examCode.getOTP().trim();
        return examCode.length() == 5;
    }

    private void disableAndEnableViews(boolean state) {
        binding.quizNameEt.setEnabled(state);
        binding.descriptionEt.setEnabled(state);
        binding.uploadQuizBtn.setEnabled(state);
        binding.secureExamCoursesSpinner.setEnabled(state);
        binding.courseQuizBtn.setEnabled(state);
        binding.secureExamBtn.setEnabled(state);
        binding.publicBtn.setEnabled(state);
        binding.privateBtn.setEnabled(state);
    }

    private void download() {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("excel file example").child("ques.xlsx");
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            String url = uri.toString();
            downloadFile(AddNewExamActivity.this,
                    DIRECTORY_DOWNLOADS, url);
            disableAndEnableViews(true);
            binding.examInstructionsLayout.setVisibility(View.GONE);
        }).addOnFailureListener(e -> {
            binding.downloadBtn.setEnabled(true);
            Toast.makeText(AddNewExamActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });


    }

    private void downloadFile(Context context,
                              String fileDestinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager)
                context.getSystemService(DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.
                Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, fileDestinationDirectory,
                "Excel Sheet Example" + ".xlsx");
        downloadManager.enqueue(request);
    }

}