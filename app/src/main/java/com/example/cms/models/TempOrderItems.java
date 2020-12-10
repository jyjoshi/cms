package com.example.cms.models;

public class TempOrderItems {
    String phone;
    String foodName;
    int qty;
    int result;

    public TempOrderItems(){}

    public TempOrderItems(String phone, String foodName, int qty, int result) {
        this.phone = phone;
        this.foodName = foodName;
        this.qty = qty;
        this.result = result;
    }

    public TempOrderItems(String phone, String foodName, int qty) {
        this.phone = phone;
        this.foodName = foodName;
        this.qty = qty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
