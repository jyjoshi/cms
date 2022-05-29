package com.example.cms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cms.R;
import com.example.cms.models.UserDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private TextView phoneNumber;
    private EditText email;
    private EditText address;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        firstName = findViewById(R.id.editTextProfFirstName);
        lastName = findViewById(R.id.editTextProfLastName);
        phoneNumber = findViewById(R.id.editTextProfPhone);
        email = findViewById(R.id.editTextProfEmail);
        address = findViewById(R.id.editTextPostalAddress);
        saveBtn = findViewById(R.id.saveBtn);

        phoneNumber.setText((String)b.get("phoneNo"));

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://canteen-management-systems-20a8c.asia-southeast1.firebasedatabase.app/");
        DatabaseReference dbRef = database.getReference("Users");
        dbRef.child((String)b.get("phoneNo")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDB temp = snapshot.getValue(UserDB.class);
                firstName.setText(temp.getFirstName());
                lastName.setText(temp.getLastName());
                if(temp.getEmail() !=null){
                    email.setText(temp.getEmail());
                }
                if(temp.getPostalAddress()!=null){
                    address.setText(temp.getPostalAddress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstName.getText().toString().trim();
                String lName = lastName.getText().toString().trim();
                String emailAddress = email.getText().toString().trim();
                String postalAddress = address.getText().toString().trim();

                DatabaseReference ref = dbRef.child((String)b.get("phoneNo"));
                ref.child("firstName").setValue(fName);
                ref.child("lastName").setValue(lName);
                ref.child("email").setValue(emailAddress);
                ref.child("postalAddress").setValue(postalAddress);

                Toast.makeText(EditProfile.this, "Changes Saved successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditProfile.this,HomeActivity.class);
                intent.putExtra("phone", (String)b.get("phoneNo"));
                intent.putExtra("firstName", fName);
                intent.putExtra("lastName", lName);
                startActivity(intent);
            }
        });
    }
}