package com.example.cms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cms.R;
import com.example.cms.models.UserDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    Button verify_btn;
    EditText phoneNumber;
    ProgressBar progressBar;
    String verificationId;

    String phone;
    String firstName;
    String lastName;
    String password;

    String requirement;
    String entryPoint;

    UserDB user;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null){
            phone = (String) b.get("phoneNo");
            requirement = (String) b.get("requirement");
            if(requirement.equals("sign_up")) {
                entryPoint = (String) b.get("entryPoint");
                firstName = (String) b.get("firstName");
                lastName = (String) b.get("lastName");
                password = (String) b.get("password");
            }
        }


        verify_btn = findViewById(R.id.verify_phone_button);
        phoneNumber = findViewById(R.id.enter_phone_for_verification);
        progressBar = findViewById(R.id.progressBar_verify_phone);
        progressBar.setVisibility(View.INVISIBLE);
        reference = FirebaseDatabase.getInstance().getReference();


        String phone = getIntent().getStringExtra("phoneNo");

        sendVerificationCodeToUser(phone);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = phoneNumber.getText().toString();
                if(code.isEmpty()||code.length()<6){
                    phoneNumber.setError("Wrong OTP");
                    phoneNumber.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });
    }

    private void sendVerificationCodeToUser(String phoneNo){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNo,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    private void verifyCode(String codeByUser){
        //Code change error
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, codeByUser);
            signIn(credential);
        }
        catch(Exception e){
            phoneNumber.setError("Wrong OTP");
            Toast.makeText(this, "Wrong OTP", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }



    }
    private void signIn(PhoneAuthCredential credential){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Do what you want.
                    if(requirement.equals("sign_up")) {
                        if(entryPoint.equals("Main")) {
                            user = new UserDB(phone, firstName, lastName, password);
                        }
                        else if (entryPoint.equals("Admin")){
                            user = new UserDB(phone, firstName, lastName, password, true);
                        }
                        reference.child("Users").child(phone).setValue(user);
                        Toast.makeText(VerifyPhoneActivity.this, "Account successfully created!", Toast.LENGTH_SHORT).show();
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else if(requirement.equals("password_reset")){
                        Intent intent = new Intent(getApplicationContext(), PasswordResetActivity.class).putExtra("phoneNo",phone);
                        startActivity(intent);

                    }
                }
                else{
                    Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}