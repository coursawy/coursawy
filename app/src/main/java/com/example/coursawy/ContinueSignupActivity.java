package com.example.coursawy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursawy.database.Database;
import com.example.coursawy.database.UserDao;
import com.example.coursawy.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.coursawy.database.UserDao.STUDENT_DATA;
import static com.example.coursawy.database.UserDao.STUDENT_DOCUMENT;

public class ContinueSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = "MainActivity";
    Spinner spinner_faculty, spinner_grade;
    final static int Galary_Pic = 1;
    private CircleImageView profImage;
    private EditText proPhone, proCode;
    private TextView mDisplayDate;
    private RelativeLayout signUpBtn;
    private ImageView backBtn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //declare
    private String[] faculties;
    private String selectedFaculty;
    private String[] grades;
    private String selectedGrade;
    private String userName;
    private String email;
    private String password;
    private static String profile_image;
    private boolean isStudent;


    User user;
    FirebaseAuth auth;
    // instance for firebase storage and StorageReference
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_signup);
        initView();
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        isStudent = intent.getBooleanExtra("isStudent", false);

        createFacultySpinner();
        createGradeSpinner();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ContinueSignupActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    private void initView() {
        mDisplayDate = findViewById(R.id.Date);
        profImage = findViewById(R.id.photo);
        proPhone = findViewById(R.id.prof_phone);
        proCode = findViewById(R.id.code_et);
        spinner_faculty = findViewById(R.id.spinner_faculty);
        spinner_grade = findViewById(R.id.spinner_grade);
        signUpBtn = findViewById(R.id.sign_up_btn);
        backBtn = findViewById(R.id.back_iv);
        signUpBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void ChooseImageFromDevice(View view) {
        Intent galaryIntent = new Intent();
        galaryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galaryIntent.setType("image/*");
        startActivityForResult(galaryIntent, Galary_Pic);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Galary_Pic && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1).start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.get().load(resultUri).placeholder(R.drawable.ic_baseline_person_pin_24).into(profImage);


                //TO Save image in Firebase Storage
                saveImageToDatabase(resultUri);
            }
            else {
                Toast.makeText(this, "error: Image can't be cropped try again..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImageToDatabase(Uri resultUri) {
        // get the Firebase  storage reference
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        auth=FirebaseAuth.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference().child("profile images");

        StorageReference filePath=storageReference.child(FirebaseAuth.getInstance().getUid()+".jpg");

        filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if (task.isSuccessful()){
                    Task<Uri> result=task.getResult().getMetadata().getReference().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final String downloadUri=uri.toString();
                            profile_image = downloadUri;
                        }
                    });
                }
            }
        });
    }

    private void createFacultySpinner() {
        faculties = getResources().getStringArray(R.array.faculties);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.faculties, R.layout.color_spinner_layout);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_faculty.setAdapter(spinnerAdapter);
        spinner_faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFaculty = faculties[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createGradeSpinner() {
        grades = getResources().getStringArray(R.array.grades);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.grades, R.layout.color_spinner_layout);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_grade.setAdapter(spinnerAdapter);
        spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGrade = grades[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validatedEntryData() {
        CircleImageView image = profImage;
        String dateOfBirth = mDisplayDate.getText().toString().trim();
        String phoneNumber = proPhone.getText().toString();
        String code = proCode.getText().toString();
        String faculty = selectedFaculty;
        String grade = selectedGrade;

        if (dateOfBirth.equals("select")){
            mDisplayDate.setError("required");
            Toast.makeText(this, "Please select your birth date", Toast.LENGTH_SHORT).show();
            return false;
        }else if (phoneNumber.isEmpty()){
            proPhone.setError("required");
            Toast.makeText(this, "Please Enter valid number", Toast.LENGTH_SHORT).show();
            return false;
        }else if (faculty == null) {
            spinner_faculty.requestFocus();
            Toast.makeText(this, "Please enter your faculty name", Toast.LENGTH_SHORT).show();
            return false;
        }else if (grade == null){
            spinner_grade.requestFocus();
            Toast.makeText(this, "Please enter your grade", Toast.LENGTH_SHORT).show();
            return false;
        }else if (code.isEmpty()) {
            proCode.setError("required");
            Toast.makeText(this, "Please enter valid code", Toast.LENGTH_SHORT).show();
            return false;
        }else if (image.getDrawable() == null) {
            image.requestFocus();
            Toast.makeText(this, "Please select your image", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void registerUser() {
        signUpBtn.setEnabled(false);
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ContinueSignupActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                            addUserToDatabase();
                        } else {
                            signUpBtn.setEnabled(true);
                            Toast.makeText(ContinueSignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUserToDatabase() {
        String dateOfBirth = mDisplayDate.getText().toString().trim();
        String phoneNumber = proPhone.getText().toString();
        String faculty = selectedFaculty;
        String grade = selectedGrade;
        String code = proCode.getText().toString();
        user = new User(auth.getUid() ,profile_image , userName , email , password , dateOfBirth , phoneNumber , faculty , grade , Integer.parseInt(code));

        if (isStudent) {
            UserDao.addStudent(user, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ContinueSignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else {
            UserDao.addDoctor(user, new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_up_btn) {
            if (validatedEntryData()) {
                registerUser();
            }
        }else if (view.getId() == R.id.back_iv){
            finish();
        }
    }
}