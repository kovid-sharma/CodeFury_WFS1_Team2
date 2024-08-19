package com.hsbc.ecommerceapp.model;

public class Customer extends User {
    private String address, phoneNumber;

    public Customer(String user_id, String username, String email, String password, String user_type, String created_at) {
        super(user_id, username, email, password, user_type, created_at);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
