package com.example.cms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.cms.R;
import com.example.cms.models.UserDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //Variables to check data entry.
    int check=1;
    int check2 = 0;

    String phoneNumber;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText phone;
    EditText password;
    String firstName;
    String lastName;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            String phoneNo = currentUser.getPhoneNumber().substring(3);
            Log.d("FirebaseAuth Phone", "Phone Number is " + phoneNo);
            FirebaseDatabase.getInstance().getReference("Users").child(phoneNo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        /*for(DataSnapshot userSnapshot : snapshot.getChildren()){
                            UserDB temp = userSnapshot.getValue(UserDB.class);
                            if (Objects.requireNonNull(temp).getPhone()!=null)
                                if (temp.getPhone().equals(phoneNo)) {
                                    firstName = temp.getFirstName();
                                    lastName = temp.getLastName();
                                    break;
                                }*/
                        UserDB temp = snapshot.getValue(UserDB.class);
                        firstName = temp.getFirstName();
                        lastName = temp.getLastName();
                    }
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //Check getApplicationContext() ; I put it because it didn't give error.
                        intent.putExtra("phone", phoneNo);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("lastName", lastName);
                        startActivity(intent);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    @Override
    public void onBackPressed() {
    }

    /**
     *Redirects new users to the sign-up page
     * @param view
     */
    public void toSignUp(View view){
        Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
        intent.putExtra("entryPoint", "Main"); // Signifying that the user is a student.
        startActivity(intent);
    }


    /**
     * Validates the login credentials of users and redirects authorized users to the homepage.
     * @param view
     */
    public void toLogin(View view){
        checkDataEntered();
        if (check == 1) {
            if (phone.getText().toString().equals("admin") && (password.getText().toString().equals("admin"))){
                startActivity(new Intent(view.getContext(), AdminHomeActivity.class));
            }
            else{
                dbRef.child("Users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                UserDB temp = userSnapshot.getValue(UserDB.class);
                                if (Objects.requireNonNull(temp).getPhone()!=null)
                                    if (temp.getPhone().equals(phone.getText().toString())) {
                                        if (temp.getPassword().equals(password.getText().toString())) {
                                            check2 = 1;
                                            Intent intent = new Intent(view.getContext(), VerifyPhoneActivity.class);
                                            intent.putExtra("phoneNo", phone.getText().toString());
                                            intent.putExtra("requirement", "login");
                                            startActivity(intent);
                                        } else {
                                            password.setError("Password Incorrect");
                                        }
                                        break;

                                    }
                            }

                            if(check2==1){

                                /*Intent intent = new Intent(view.getContext(), HomeActivity.class);
                                intent.putExtra("phone", phoneNumber);
                                intent.putExtra("firstName", firstName);
                                intent.putExtra("lastName", lastName);
                                startActivity(intent);*/

                                /*Bundle bundle = new Bundle();
                                bundle.putString("phone", phoneNumber);
                                bundle.putString("firstName", firstName);
                                bundle.putString("lastName", lastName);

                                OrderFragment fragment = new OrderFragment();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                fragment.setArguments(bundle);
                                transaction.add(R.id.nav_host_fragment_container, fragment, "fragment_order");
                                transaction.commit();
*/
                            }
                            else{
                                phone.setError("Phone number is not registered.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    }

    /**
     * Makes sure no fields are left empty
     */
    public void checkDataEntered() {
        check = 1;
        if (isEmpty(phone)){
            phone.setError("Enter phone number");
            check = 0;
        }
        if (isEmpty(password)){
            password.setError("Enter password");
            check = 0;
        }


    }

    /**
     * To check whether a particular field is empty or not.
     * @param text text present in the Text Field.
     * @return true if field is empty
     */
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void toEnterPhone(View view) {
        startActivity(new Intent(this, EnterPhoneActivity.class));
    }

}