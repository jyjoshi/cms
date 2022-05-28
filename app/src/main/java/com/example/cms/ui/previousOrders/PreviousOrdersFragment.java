package com.example.cms.ui.previousOrders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cms.models.Bill;
import com.example.cms.activities.HomeActivity;
import com.example.cms.adapters.PreviousOrdersAdapter;
import com.example.cms.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreviousOrdersFragment extends Fragment {
    DatabaseReference dbref;
    private ArrayList<String> transactionId;
    private ArrayList<String> phone;
    private ArrayList<String> time;
    private ArrayList<String> amount;
    private ArrayList<String> token;

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    PreviousOrdersAdapter viewOrderAdapter;

    private String phoneNumber;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_previous_orders, container, false);

        phoneNumber = ((HomeActivity) getActivity()).getPhoneNumber();
        transactionId = new ArrayList<>();
        phone = new ArrayList<>();
        time = new ArrayList<>();
        amount = new ArrayList<>();
        token = new ArrayList<>();

        dbref = FirebaseDatabase.getInstance("https://canteen-management-systems-20a8c.asia-southeast1.firebasedatabase.app//").getReference("Bill");

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView = root.findViewById(R.id.recyclerview_view_order);
        recyclerView.setLayoutManager(linearLayoutManager);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Bill temp = dataSnapshot.getValue(Bill.class);
                    if(temp.getPhone().equals(phoneNumber)) {
                        transactionId.add(temp.getTransactionId().toString());
                        phone.add(temp.getPhone());
                        amount.add(temp.getTotalPrice());
                        time.add(temp.getTime());
                        token.add(temp.getToken().toString());
                        viewOrderAdapter = new PreviousOrdersAdapter(getContext(), transactionId, phone, time, amount, token);
                        recyclerView.setAdapter(viewOrderAdapter);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return root;
    }
}