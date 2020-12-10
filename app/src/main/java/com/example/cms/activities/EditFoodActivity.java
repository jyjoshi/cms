package com.example.cms.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cms.R;
import com.example.cms.models.MenuItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditFoodActivity extends AppCompatActivity {
    private String name;
    private String stringUri;
    private String description;
    private String price;
    private EditText foodName;
    private EditText foodDescription;
    private EditText foodPrice;
    private ImageView imgIcon;
    private ProgressBar progressBar;
    private Uri imageUri;
    private int check =1;
    private StorageReference reference;
    private String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_editor);

        reference = FirebaseStorage.getInstance().getReference();
        imgIcon = findViewById(R.id.imageView2);
        foodDescription = findViewById(R.id.editFoodDescription);
        foodName = findViewById(R.id.editFoodName);
        foodPrice = findViewById(R.id.editPrice);

        Button addImage = findViewById(R.id.addImage);
        progressBar = findViewById(R.id.progressBar);


        progressBar.setVisibility(View.INVISIBLE);

        Intent intentIn = getIntent();
        Bundle b = intentIn.getExtras();
        if(b!=null){
            name = (String) b.get("name");
            description = (String) b.get("desc");
            price = (String) b.get("price");
            stringUri = (String) b.get("uri");
            uid = (String) b.get("uid");
        }

        Picasso.get().load(Uri.parse(stringUri)).into(imgIcon);
        foodPrice.setText(price);
        foodName.setText(name);
        foodDescription.setText(description);
        foodPrice.setText(price);

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);

            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri!=null){
                    uploadToFirebase(imageUri);
                }
                else{
                    Toast.makeText(EditFoodActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            imgIcon.setImageURI(imageUri);


        }
    }

    public void addToMenu(View view) {
        checkDataEntered();
        if(check==1)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            MenuItem menuItem = new MenuItem(
                    stringUri,
                    uid,
                    foodName.getText().toString(),
                    foodDescription.getText().toString(),
                    foodPrice.getText().toString()
            );
            database.getReference().child("Menu").child(uid).setValue(menuItem);
            stringUri = "";

        }

    }


    private void uploadToFirebase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressBar.setVisibility(View.INVISIBLE);
                        stringUri = uri.toString();
                        Log.i("Value of stringUri", stringUri);
                        Toast.makeText(EditFoodActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(EditFoodActivity.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        check=1;
        if (isEmpty(foodName)) {
            foodName.setError("Name is required");
            check=0;
        }
        if (isEmpty(foodDescription)) {
            foodDescription.setError("Description is required");
            check=0;
        }

        if (isEmpty(foodPrice)) {
            foodPrice.setError("Last name is required!");
            check=0;
        }/*
        if (stringUri.equals("")){
            foodDescription.setError("Uri can't be empty");
            check=0;
        }*/


    }
}