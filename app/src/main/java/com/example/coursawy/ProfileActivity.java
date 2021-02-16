package com.example.coursawy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.coursawy.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    ListView profileItemsList;
    ProfileInfoAdapter profileInfoAdapter;
    ArrayList<ProfileInfoItem> profileInfoItems;
    ImageView backIv;
    ImageView profileImageView;
    ImageView changeProfileImageView;
    DatabaseReference reference;
    final static int GALLERY_PIC = 2;
    private Uri uriImage;
    private StorageReference storageReference;
    private boolean isStudent = false;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);

        profileItemsList = findViewById(R.id.profile_items_list);
        changeProfileImageView = findViewById(R.id.select_image_btn);
        backIv = findViewById(R.id.back_iv);
        profileImageView = findViewById(R.id.profile_image);
        profileInfoItems = new ArrayList<>();
        backIv.setOnClickListener(v -> onBackPressed());
        changeProfileImageView.setOnClickListener(v -> {
            Intent galleryIntent = new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, GALLERY_PIC);
        });
        if (isStudent) {
            storageReference = FirebaseStorage.getInstance().getReference().child("profile images").child("students");
        } else {
            storageReference = FirebaseStorage.getInstance().getReference().child("profile images").child("doctors");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PIC && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1).start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.get().load(resultUri).placeholder(R.drawable.ic_baseline_person_pin_24).into(profileImageView);
                uriImage = resultUri;
            } else {
                Toast.makeText(this, "error: Image can't be cropped try again..", Toast.LENGTH_SHORT).show();
            }
        }
        StorageReference filePath = storageReference.child(currentUserID + ".jpg");

        filePath.putFile(uriImage).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                Task<Uri> result = task.getResult().getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(uri -> {
                    final String downloadUri = uri.toString();
                    reference.child("profileImage").setValue(downloadUri)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Toast.makeText(ProfileActivity.this, "Profile image store in firebase database", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ProfileActivity.this, "error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                });
            }
        });
    }

    public void setIsStudent(boolean student) {
        isStudent = student;
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String type = user.getType();
                profileInfoItems.clear();
                profileInfoItems.add(new ProfileInfoItem("Name:", user.getFullname()));
                profileInfoItems.add(new ProfileInfoItem("Username:", user.getUsername()));
                profileInfoItems.add(new ProfileInfoItem("Date of Birth:", user.getDateOfBirth()));
                profileInfoItems.add(new ProfileInfoItem("Phone Number:", user.getPhoneNumber()));
                profileInfoItems.add(new ProfileInfoItem("Faculty:", user.getFaculty()));
                profileInfoItems.add(new ProfileInfoItem("Grade:", user.getGrade()));
                if (type.equals("student")) {
                    profileInfoItems.add(new ProfileInfoItem("Code:", "170140"));
                    setIsStudent(true);
                }
                profileInfoAdapter = new ProfileInfoAdapter(ProfileActivity.this, profileInfoItems);
                profileItemsList.setAdapter(profileInfoAdapter);
                if (user.getProfileImage().equals("default")) {
                    profileImageView.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(getApplicationContext()).load(user.getProfileImage()).into(profileImageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}