package com.example.cms.models;

import android.view.Menu;

import java.util.ArrayList;

public class Bill {
    String time;
    String totalPrice;
    String transactionId;
    String phone;
    String address;

    public Bill(){}

    public Bill(String time, String totalPrice, String transactionId, String phone) {
        this.time = time;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
        this.phone = phone;
    }

    public Bill(String time, String totalPrice, String transactionId, String phone, String address) {
        this.time = time;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
        this.phone = phone;
        this.address = address;
    }

    public String getAddress(){ return address; }

    public String getTime() {
        return time;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPhone() {
        return phone;
    }
}


