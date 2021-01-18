package com.example.coursawy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContinueSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    @BindView(R.id.Date)
    TextView mDisplayDate;
    @BindView(R.id.spinner_faculty)
    Spinner spinner_faculty;
    @BindView(R.id.student_spinner_grade)
    Spinner student_spinner_grade;
    @BindView(R.id.doctor_spinner_grade)
    Spinner doctor_spinner_grade;
    @BindView(R.id.department_lable)
    TextView departmentLabel;
    @BindView(R.id.code_lable)
    TextView codeLabel;
    @BindView(R.id.continue_department)
    EditText department;
    @BindView(R.id.continue_code)
    EditText code;
    @BindView(R.id.continue_profile_phone)
    EditText phone;
    @BindView(R.id.signin_btn)
    Button signUpBtn;
    @BindView(R.id.continue_profile_pic)
    CircleImageView profImage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    private static final String TAG = "MainActivity";
    final static int Galary_Pic = 1;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private String[] faculties;
    private String selectedFaculty;
    private String[] studentGrades;
    private String[] doctorGrades;
    private String selectedStudentGrade;
    private String selectedDoctorGrade;
    private String userName,fullname,email,password,currentUserID;


    private FirebaseAuth auth;
    private DatabaseReference userRef;
    private StorageReference storageReference;
    private boolean isStudent;
    private Uri uriImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_signup);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        fullname = intent.getStringExtra("fullName");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        isStudent = intent.getBooleanExtra("isStudent", false);

        auth=FirebaseAuth.getInstance();



        if (isStudent){
            storageReference= FirebaseStorage.getInstance().getReference().child("profile images").child("students");
        }
        else {
            storageReference= FirebaseStorage.getInstance().getReference().child("profile images").child("doctors");
        }

        if (!isStudent){

            createFacultySpinner();
            createDoctorGradeSpinner();

            student_spinner_grade.setVisibility(View.GONE);
            codeLabel.setVisibility(View.GONE);
            code.setVisibility(View.GONE);
            doctor_spinner_grade.setVisibility(View.VISIBLE);
            departmentLabel.setVisibility(View.VISIBLE);
            department.setVisibility(View.VISIBLE);
        }
        else {

            createFacultySpinner();
            createStudentGradeSpinner();

            doctor_spinner_grade.setVisibility(View.GONE);
            departmentLabel.setVisibility(View.GONE);
            department.setVisibility(View.GONE);
            student_spinner_grade.setVisibility(View.VISIBLE);
            codeLabel.setVisibility(View.VISIBLE);
            code.setVisibility(View.VISIBLE);
        }




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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void ChooseImageFromDevice() {
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
            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1).start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.get().load(resultUri).placeholder(R.drawable.ic_baseline_person_pin_24).into(profImage);
                uriImage = resultUri;
            }
            else {
                Toast.makeText(this, "error: Image can't be cropped try again..", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void createNewAccount(Uri resultUri){
        signUpBtn.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ContinueSignupActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                            addUserToDatabase(resultUri);
                        } else {
                            signUpBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ContinueSignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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

    private void createStudentGradeSpinner() {
        studentGrades = getResources().getStringArray(R.array.studentgrades);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.studentgrades, R.layout.color_spinner_layout);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        student_spinner_grade.setAdapter(spinnerAdapter);
        student_spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStudentGrade = studentGrades[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void createDoctorGradeSpinner() {
        doctorGrades = getResources().getStringArray(R.array.doctorgrades);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.doctorgrades, R.layout.color_spinner_layout);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        doctor_spinner_grade.setAdapter(spinnerAdapter);
        doctor_spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDoctorGrade = doctorGrades[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validatedEntryData() {
        CircleImageView image = profImage;
        String dateOfBirth = mDisplayDate.getText().toString().trim();
        String phoneNumber = phone.getText().toString();
        String sCode = code.getText().toString();
        String docDepartment = department.getText().toString();
        String faculty = selectedFaculty;
        String stuGrade = selectedStudentGrade;
        String docGrade = selectedDoctorGrade;

        if (dateOfBirth.equals("select")){
            mDisplayDate.setError("required");
            Toast.makeText(this, "Please select your birth date", Toast.LENGTH_SHORT).show();
            return false;
        }else if (phoneNumber.isEmpty()){
            phone.setError("required");
            Toast.makeText(this, "Please Enter valid number", Toast.LENGTH_SHORT).show();
            return false;
        }else if (faculty == null || faculty.equals("Select")) {
            spinner_faculty.requestFocus();
            Toast.makeText(this, "Please enter your faculty name", Toast.LENGTH_SHORT).show();
            return false;
        }else if (image.getDrawable() == null) {
            image.requestFocus();
            Toast.makeText(this, "Please select your image", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isStudent){
            if (stuGrade == null || stuGrade.equals("Select")){
                student_spinner_grade.requestFocus();
                Toast.makeText(this, "Please enter your grade", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (sCode.isEmpty()) {
                code.setError("required");
                Toast.makeText(this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (!isStudent){
            if (docGrade == null || docGrade.equals("Select")){
                doctor_spinner_grade.requestFocus();
                Toast.makeText(this, "Please enter your grade", Toast.LENGTH_SHORT).show();
                return false;
            }
           else if (docDepartment.isEmpty()) {
                department.setError("required");
                Toast.makeText(this, "Please enter valid department", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }



    private void addUserToDatabase(Uri resultUri) {
        String dateOfBirth = mDisplayDate.getText().toString().trim();
        String phoneNumber = phone.getText().toString();
        String faculty = selectedFaculty;

        currentUserID=auth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);


        StorageReference filePath = storageReference.child(currentUserID + ".jpg");

        filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if (task.isSuccessful()) {
                    Task<Uri> result = task.getResult().getMetadata().getReference().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final String downloadUri = uri.toString();
                            userRef.child("profileImage").setValue(downloadUri)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(ContinueSignupActivity.this, "Profile image store in firebase database", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(ContinueSignupActivity.this, "error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });
                }
            }
        });

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("username", userName);
        userMap.put("fullname", fullname);
        userMap.put("email", email);
        userMap.put("password", password);
        userMap.put("dateOfBirth", dateOfBirth);
        userMap.put("phoneNumber", phoneNumber);
        userMap.put("faculty", faculty);
        if (isStudent){
            userMap.put("grade", selectedStudentGrade);
            userMap.put("code", code.getText().toString().trim());
        }
        else {
            userMap.put("grade", selectedDoctorGrade);
            userMap.put("department", department.getText().toString());
        }

        userRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    sendEmailVerificationMessage();
                    Toast.makeText(ContinueSignupActivity.this, "Data saved successfully in database", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUpBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ContinueSignupActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void sendEmailVerificationMessage(){

        FirebaseUser user=auth.getCurrentUser();

        if (user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        signUpBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ContinueSignupActivity.this, "Registeration successfull. please check your emal to verify account..", Toast.LENGTH_LONG).show();
                        SendUserToLoginActivity();
                        auth.signOut();
                    }
                    else {
                        signUpBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ContinueSignupActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        auth.signOut();
                    }
                }
            });
        }
    }

    private void SendUserToLoginActivity() {
        Intent intent=new Intent(ContinueSignupActivity.this, SignActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("isStudent",isStudent);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.continue_back_to_main_btn) {
            startActivity(new Intent(ContinueSignupActivity.this,MainActivity.class));
            finish();
        }
        else if (view.getId() == R.id.continue_back_btn){
            finish();
        }
        else if (view.getId()==R.id.select_image_btn){
            ChooseImageFromDevice();
        }
        else if (view.getId()==R.id.signin_btn){
            if (validatedEntryData()){
                createNewAccount(uriImage);
            }
        }
    }
}