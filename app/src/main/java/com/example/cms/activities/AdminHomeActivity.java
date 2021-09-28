package com.example.cms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cms.R;

public class AdminHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void toOrderList(View view) {
        startActivity(new Intent(view.getContext(), AdminOrderActivity.class));
    }

    public void toMenu(View view) {
        startActivity(new Intent(view.getContext(), AdminViewMenuActivity.class));
    }

    public void toAdminMenuEditOptions(View view) {
        startActivity(new Intent(view.getContext(), AdminMenuEditOptionsActivity.class));
    }

    public void toTeacherSignUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        intent.putExtra("entryPoint", "Admin");
        startActivity(intent);
    }
}
