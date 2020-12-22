package com.example.cms.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.cms.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //Putting thread to sleep so that the firebase valueEventListener can properly get executed before the deletion of tempOrderItems node.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Deleting the temporary node created.
        DatabaseReference deleter = FirebaseDatabase.getInstance().getReference();
        deleter.child("temp").removeValue();
        /*deleter.child("tempOrderItems").removeValue();*/

        /*//After data has been passed to Order Items we can safely delete this node.
        DatabaseReference deleter = FirebaseDatabase.getInstance().getReference();
        deleter.child("tempOrderItems").removeValue();*/


    }

}
