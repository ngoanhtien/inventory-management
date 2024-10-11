package com.management.model;

import com.management.utils.ErrorLogger;

import java.util.Set;

public class Customer {
    private String id; // Id: bắt buộc, không được trùng
    private String name; // Name: không được để trống
    private String email; // Email: không được trùng, định dạng hợp lệ
    private String phoneNumber; // PhoneNumber: không được trùng, định dạng hợp lệ

    // Constructors
    public Customer(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Override equals và hashCode nếu cần
    // ...
}
