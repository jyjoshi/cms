package com.example.cms.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cms.activities.HomeActivity;
import com.example.cms.adapters.MenuAdapter;
import com.example.cms.models.MenuItem;
import com.example.cms.R;
import com.example.cms.activities.TestActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class OrderFragment extends Fragment {

    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://canteen-management-systems-20a8c.asia-southeast1.firebasedatabase.app//");
    private DatabaseReference dbref = database.getReference("Menu");

    private String phoneNumber;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    RecyclerView menuList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            phoneNumber = ((HomeActivity) getActivity()).getPhoneNumber();
            //Deleting the temporary node created.
            DatabaseReference deleter = FirebaseDatabase.getInstance("https://canteen-management-systems-20a8c.asia-southeast1.firebasedatabase.app/").getReference();
            deleter.child("temp").child(phoneNumber).removeValue();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        initDataset();
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        menuList = (RecyclerView) root.findViewById(R.id.menuList);
        menuList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;

    }

    public void initDataset() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    menuItems.add(dataSnapshot.getValue(MenuItem.class));
                    quantity.add(0);
                }
                MenuAdapter adapter = new MenuAdapter(menuItems, quantity, phoneNumber);
                menuList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goToCart() {
        startActivity(new Intent(getActivity(), TestActivity.class));
    }
}