package com.example.cms.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cms.R;
import com.example.cms.models.MenuItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class MenuEditorActivity extends AppCompatActivity {
    private int check;

    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri imageUri;
    private String stringUri;

    private EditText foodName;
    private EditText foodPrice;
    private EditText foodDescription;
    private Boolean imagePresent;

    private final StorageReference reference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        check = 0;
        stringUri="";
        imagePresent = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_editor);

        Button addImage = findViewById(R.id.addImage);
        imageView = findViewById(R.id.imageView2);
        progressBar = findViewById(R.id.progressBar);

        foodName = findViewById(R.id.editFoodName);
        foodDescription = findViewById(R.id.editFoodDescription);
        foodPrice = findViewById(R.id.editPrice);

        progressBar.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MenuEditorActivity.this, "Image upload failed. No image detected, so please select an image to upload.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            imagePresent = true;
        }
    }

    public void addToMenu(View view) {
        checkDataEntered();
        if (check == 1) {
            if (stringUri.equals("")) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_layout, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                Button yesbutton = (Button) popupView.findViewById(R.id.yesbtn);
                Button nobutton = (Button) popupView.findViewById(R.id.nobtn);

                yesbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check = 1;
                        popupWindow.dismiss();
                    }
                });

                nobutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check = 0;
                        popupWindow.dismiss();
                    }
                });

            }
        }
        if(check==1)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            String key = database.getReference("Menu").push().getKey();
            Map<String, Object> childUpdates = new HashMap<>();
            MenuItem menuItem = new MenuItem(
                    stringUri,
                    key,
                    foodName.getText().toString(),
                    foodDescription.getText().toString(),
                    foodPrice.getText().toString()
            );
            database.getReference().child("Menu").child(key).setValue(menuItem);
            Toast.makeText(this, "Item added successfully", Toast.LENGTH_LONG).show();
            stringUri = "";
            foodName.setText("");
            foodDescription.setText("");
            foodPrice.setText("");
            imageUri = null;

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
                        Toast.makeText(MenuEditorActivity.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
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
                Toast.makeText(MenuEditorActivity.this, "Uploading Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Get the extension type of a particular file, in this case the image file.
     * @param uri - uniform resource identifier of file
     * @return File extension type
     */
    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    /**
     * Make sure that no fields required for a menu item are left empty.
     */
    private void checkDataEntered() {
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
        }

//        if (imagePresent = false){
//            Toast.makeText(this, "No image inserted", Toast.LENGTH_LONG);
//            check =0;
//        }


    }

}
