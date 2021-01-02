
package com.example.coursawy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.coursawy.database.UserDao;
import com.example.coursawy.model.Exam;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends AppCompatActivity {


    @BindView(R.id.online_btn)
    Button onlineBtn;
    @BindView(R.id.offline_btn)
    Button offlineBtn;
    @BindView(R.id.add_test_btn)
    Button addTestBtn;
    List<String> questions;
    List<String> answers1;
    List<String> answers2;
    List<String> answers3;
    List<String> answers4;
    List<String> correctAnswers;

    private boolean isStudent;
    private List<Exam> allQuestionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);

        isStudent = getIntent().getBooleanExtra("isStudentLogin", false);

        if (isStudent==false)
            addTestBtn.setVisibility(View.VISIBLE);
        else
            addTestBtn.setVisibility(View.GONE);


        //get questions from firebase
//        UserDao.getExam(FirebaseAuth.getInstance().getUid(), task -> {
//            if (task.isSuccessful()){
//
//                Exam exam = task.getResult().toObject(Exam.class);
//
//                Log.e("Exams" , exam.getQuestion() + "\n" + exam.getAns1() + "\n" +
//                        exam.getAns2() + "\n" + exam.getAns3() + exam.getAns3());
//            }
//        });


//        onlineBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(com.momen.giants.ExamActivity.this, TestsListActivity.class);
//                intent.putExtra("type", "online");
//                startActivity(intent);
//            }
//        });
        offlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this, TestsListActivity.class);
                intent.putExtra("type", "offline");
                startActivity(intent);
            }
        });

        addTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                startActivityForResult(intent.createChooser(intent,"Select Excel Files"),430);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==430 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
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
            for (Row row: sheet) {
                for (int i = 0 ; i < row.getHeight() ; i++) {
                    String cellValue = dataFormatter.formatCellValue(row.getCell(i));
                    switch (i){
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
            addQuestionsToFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addQuestionsToFirebase() {
        for (int i = 0; i < questions.size() ; i++){
            Log.e("Exams" , questions.get(i) +
                    answers1.get(i) + answers2.get(i) + answers3.get(i) +
                    answers4.get(i) + correctAnswers.get(i));
            Exam exam = new Exam(FirebaseAuth.getInstance().getUid(),questions.get(i) ,
                    answers1.get(i) , answers2.get(i) , answers3.get(i) ,
                    answers4.get(i) , correctAnswers.get(i));
            UserDao.addExam(exam, o -> {
                Toast.makeText(this, "Exam Added Successfully", Toast.LENGTH_LONG).show();
            }, e -> {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            });
        }
    }
}