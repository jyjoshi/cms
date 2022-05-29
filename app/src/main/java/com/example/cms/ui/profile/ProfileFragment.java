package com.example.cms.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cms.activities.EditProfile;
import com.example.cms.activities.HomeActivity;
import com.example.cms.R;
import com.example.cms.activities.MainActivity;
import com.example.cms.models.UserDB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private String firstName;
    private String lastName;
    private String phone;
    private String fullName;
    private Button signOutButton;
    private ImageButton editProfileButton;

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        signOutButton = root.findViewById(R.id.btnSignOut);
        editProfileButton = root.findViewById(R.id.editProfileButton);

        signOutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(v.getContext(), MainActivity.class));

        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editProfileIntent = new Intent(getContext(), EditProfile.class);
                editProfileIntent.putExtra("phoneNo",phone);
                startActivity(editProfileIntent);
            }
        });

        firstName = ((HomeActivity)getActivity()).getFirstName();
        lastName = ((HomeActivity)getActivity()).getLastName();
        phone = ((HomeActivity)getActivity()).getPhoneNumber();

        TextView name = root.findViewById(R.id.textProfName);
        TextView phoneNum = root.findViewById(R.id.textProfPhone);
        fullName = firstName+" "+lastName;

        name.setText(fullName);
        phoneNum.setText(phone);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://canteen-management-systems-20a8c.asia-southeast1.firebasedatabase.app/");
        DatabaseReference dbRef = database.getReference("Users");
        TextView email = root.findViewById(R.id.textProfEmail);
        TextView address = root.findViewById(R.id.textPostalAddress);
        dbRef.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDB temp = snapshot.getValue(UserDB.class);
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

        return root;
    }


}