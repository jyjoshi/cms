package com.example.cms.models;

public class UserDB {
    private String firstName;
    private String lastName;
    private String password;
    private String phone;

    public UserDB(String firstName, String lastName, String password, String phone, String email, String postalAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.postalAddress = postalAddress;
        this.isTeacher = false;
    }

    private String email;
    private String postalAddress;
    private Boolean isTeacher;

    public UserDB(){}

    public UserDB(String phone, String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.isTeacher = false;
    }

    public UserDB(String phone, String firstName, String lastName, String password, Boolean isTeacher) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.isTeacher = isTeacher;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getIsTeacher(){ return isTeacher;}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }
}
