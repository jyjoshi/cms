package com.example.cms.activities;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cms.R;
import com.example.cms.adapters.CartAdapter;
import com.example.cms.models.Bill;
import com.example.cms.models.FoodQuantity;
import com.example.cms.models.MenuItem;
import com.example.cms.models.OrderedItem;
import com.example.cms.models.UserDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    String phoneNumber;
    ArrayList<String> foodName;
    ArrayList<Integer> quantity;
    ArrayList<Integer> price;
    ArrayList<Integer> result;
    String transactionId;
    String time;
    TextView costDisplay;
    DatabaseReference root;
    DatabaseReference retrieveTemp;
    ArrayList<FoodQuantity> itemsOrdered;
    private EditText address;
    private String stringAddress;

    int totalCost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        costDisplay = findViewById(R.id.total_cost);
        recyclerView = findViewById(R.id.recyclerview_view_order);
        address = findViewById(R.id.address);

        foodName = new ArrayList<>();
        quantity = new ArrayList<>();
        price = new ArrayList<>();
        result = new ArrayList<>();
        itemsOrdered = new ArrayList<>();

        stringAddress = "";

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Intent intentIn = getIntent();
        Bundle b = intentIn.getExtras();
        if(b!=null){
            phoneNumber = (String) b.get("phone");
        }

        retrieveTemp = FirebaseDatabase.getInstance().getReference("temp");
        retrieveTemp.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                for(MutableData data : currentData.getChildren()){
                    FoodQuantity temp = data.getValue(FoodQuantity.class);
                    //TempOrderItems tempOrderItems = new TempOrderItems(phoneNumber, temp.getFoodName(), temp.getQuantity());
                    FirebaseDatabase.getInstance().getReference().child("tempOrderItems").child(phoneNumber).child(temp.getFoodName()).setValue(temp.getQuantity());
                }
                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {

            }
        });
        Log.i("items ", String.valueOf(itemsOrdered.size()));

        cartAdapter = new CartAdapter(foodName, quantity, price, result);
        recyclerView.setAdapter(cartAdapter);
        String stringForCostDisplay = "Total : "+totalCost+" Rs";
        costDisplay.setText(stringForCostDisplay);



        // Name, Quantity and Price are being retrieved properly
        root = FirebaseDatabase.getInstance().getReference();


        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshotChildren : snapshot.child("temp").getChildren()){
                    FoodQuantity temp = snapshotChildren.getValue(FoodQuantity.class);
                    foodName.add(temp.getFoodName());
                    quantity.add(temp.getQuantity());
                    Log.i("FQ Add", "Added : to foodName " + foodName+" Quantitiy" + quantity);
                    for(DataSnapshot snapshot1 : snapshot.child("Menu").getChildren()) {
                        MenuItem temp2 = snapshot1.getValue(MenuItem.class);
                        if (temp.getFoodName().equals(temp2.getName())) {
                            //Retrieving the price and calculating result for the elements that have been added to the cart
                            price.add(Integer.parseInt(temp2.getPrice()));
                            result.add(temp.getQuantity()*Integer.parseInt(temp2.getPrice()));

                            //Display the total cost of all the items added to the cart
                            totalCost = sum(result);
                            String stringForCostDisplay = "Total : "+totalCost+" Rs";
                            costDisplay.setText(stringForCostDisplay);


                            Log.i("Price Add", "Added Price : " + price+" Price * Qty "+result + " Total Bill is :" + totalCost);


                            //Setting the items in the RecyclerView.
                            cartAdapter = new CartAdapter(foodName, quantity, price, result);
                            recyclerView.setAdapter(cartAdapter);

                            /*DatabaseReference totemp2 = FirebaseDatabase.getInstance().getReference().child("temp2");
                            for(int i=0; i<foodName.size(); i++){
                                totemp2.child(String.valueOf(i+1)).setValue(new MenuItem(foodName.get(i), String.valueOf(price.get(i)), String.valueOf(quantity.get(i)), String.valueOf(result.get(i))));
                            }*/

                        }
                    }

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
        recyclerView.setAdapter(cartAdapter);
    }

    //To generate the total cost of the items ordered.
    public int sum(ArrayList<Integer> x){
        int total =0;
        for(int i : x){
            total+=i;
        }
        return total;
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public void placeOrder(View view) {


        Log.i("TAG", "INSIDE ORDER FUNCTION");
        stringAddress = address.getText().toString();

        //Storing time when order was placed
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time = dtf.format(now);

        //Generating transaction id
        transactionId = root.push().getKey();
        root = FirebaseDatabase.getInstance().getReference().child("Bill");




        //Create the orderItems in database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DatabaseReference toOrderedItems = FirebaseDatabase.getInstance().getReference().child("OrderedItems");
                for(DataSnapshot snapshot1 : snapshot.child("tempOrderItems").child(phoneNumber).getChildren()){
                    Log.i("TAG", "INSIDE TEMPORDERITEMS");
                    String name = snapshot1.getKey();
                    int qty = snapshot1.getValue(Integer.class);
                    for(DataSnapshot snapshot11 : snapshot.child("Menu").getChildren()){
                        MenuItem tempItem = snapshot11.getValue(MenuItem.class);
                        if(tempItem.getName().equals(name)){
                            Log.i("TAG", "ITEM FOUND IN MENU");
                            String price = tempItem.getPrice();
                            String result = String.valueOf(Integer.parseInt(price)*qty);
                            OrderedItem ob = new OrderedItem(transactionId, name, String.valueOf(qty), price, result);
                            String key = toOrderedItems.push().getKey();
                            toOrderedItems.child(key).setValue(ob);
                            break;
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            FirebaseDatabase.getInstance().getReference().child("Users").child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserDB temp = snapshot.getValue(UserDB.class);
                    if (temp.getIsTeacher()) {
                        Log.i("TAG", "Teacher");
                        if(!stringAddress.equals("")) {
                            Bill bill = new Bill(time, String.valueOf(totalCost), transactionId, phoneNumber, stringAddress);
                            root.child(transactionId).setValue(bill);
                            Log.i("Address : ", "Entered");
                        }
                        else{
                            Log.i("Address ", "Empty");
                            Bill bill = new Bill(time, String.valueOf(totalCost), transactionId, phoneNumber);
                            root.child(transactionId).setValue(bill);
                        }
                    }
                    else {
                        Log.i("TAG ", "Student");
                        Bill bill = new Bill(time, String.valueOf(totalCost), transactionId, phoneNumber);
                        root.child(transactionId).setValue(bill);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });






        //Deleting the temporary node created.
        DatabaseReference deleter = FirebaseDatabase.getInstance().getReference();
        deleter.child("temp").removeValue();
        /*deleter.child("tempOrderItems").removeValue();*/





        /*startActivity(new Intent(this, TestActivity.class));*/
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("transactionId", transactionId);
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);

    }

}