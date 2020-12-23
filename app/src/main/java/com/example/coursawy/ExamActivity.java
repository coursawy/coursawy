package com.example.coursawy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends AppCompatActivity {


    @BindView(R.id.online_btn)
    Button onlineBtn;
    @BindView(R.id.offline_btn)
    Button offlineBtn;
    @BindView(R.id.add_test_btn)
    Button addTestBtn;

    private Uri fileUri;
    private boolean isStudent;

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
            fileUri=data.getData();

            //cotinue as image code to store in database Storage with id of doctor

        }
        }
}