package com.example.cms.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cms.activities.HomeActivity;
import com.example.cms.R;
import com.example.cms.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    private String firstName;
    private String lastName;
    private String phone;
    private String fullName;
    private Button signOutButton;

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        signOutButton = root.findViewById(R.id.btnSignOut);

        signOutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(v.getContext(), MainActivity.class));

        });

        firstName = ((HomeActivity)getActivity()).getFirstName();
        lastName = ((HomeActivity)getActivity()).getLastName();
        phone = ((HomeActivity)getActivity()).getPhoneNumber();

        EditText name = root.findViewById(R.id.editTextProfName);
        EditText phoneNum = root.findViewById(R.id.editTextProfPhone);
        fullName = firstName+" "+lastName;

        name.setText(fullName);
        phoneNum.setText(phone);

        final TextView textView = root.findViewById(R.id.text_profile_name);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


}