package com.example.cms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cms.R;

public class EnterPhoneActivity extends AppCompatActivity {
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone);

        phoneNumber = findViewById(R.id.phone_no_for_password_reset);
    }

    public void toVerifyPhone(View view) {
        Intent intent = new Intent(this, VerifyPhoneActivity.class);
        intent.putExtra("phoneNo", phoneNumber.getText().toString());
        intent.putExtra("requirement", "password_reset");
        startActivity(intent);
    }
}