package com.example.cms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.cms.R;
import com.example.cms.adapters.CurrentOrdersAdapter;
import com.example.cms.models.Bill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminCurrentOrdersActivity extends AppCompatActivity {
    DatabaseReference dbref;
    private ArrayList<String> transactionId;
    private ArrayList<String> time;
    private ArrayList<String> amount;
    private ArrayList<String> token;
    private ArrayList<String> status;
    RecyclerView recyclerView;
    CurrentOrdersAdapter currentOrdersAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_current_orders);

        token = new ArrayList<>();
        transactionId = new ArrayList<>();
//        phone = new ArrayList<>();
        time = new ArrayList<>();
        amount = new ArrayList<>();
        status = new ArrayList<>();

        dbref = FirebaseDatabase.getInstance().getReference();

        //initDataset();
        Log.i("after initializing", String.valueOf((transactionId.size() )));

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView = findViewById(R.id.recyclerview_view_order);
        recyclerView.setLayoutManager(linearLayoutManager);
        /*CurrentOrdersAdapter = new CurrentOrdersAdapter(transactionId, phone, time, amount);
        recyclerView.setAdapter(CurrentOrdersAdapter);*/

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.child("Status").getChildren()){
//                    Bill temp = dataSnapshot.getValue(Bill.class);
                    String id = dataSnapshot.getKey();
                    transactionId.add(id);
                    String statusUpdate = dataSnapshot.getValue(String.class);
                    Log.i("Logger", statusUpdate);
                    status.add(statusUpdate);

                    Bill bill = snapshot.child("Bill").child(id).getValue(Bill.class);
                    token.add(bill.getToken().toString());
                    time.add(bill.getTime());
                    amount.add(bill.getTotalPrice());

                    Log.i("Check", "transaction"+transactionId);
                    currentOrdersAdapter = new CurrentOrdersAdapter(AdminCurrentOrdersActivity.this, transactionId, status, time, amount, token);
                    recyclerView.setAdapter(currentOrdersAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(currentOrdersAdapter);

    }

}