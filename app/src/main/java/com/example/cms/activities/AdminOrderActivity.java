package com.example.cms.activities;
import com.example.cms.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);
    }

    public void toCurrentOrders(View view) {
        startActivity(new Intent(view.getContext(), AdminCurrentOrdersActivity.class));
    }

    public void toAllOrder(View view) {
        startActivity(new Intent(view.getContext(), AdminViewOrderActivity.class));
    }
}