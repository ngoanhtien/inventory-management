package com.management.model;

import com.management.utils.ErrorLogger;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class Order {
    private String id; // Id: bắt buộc, không được trùng
    private String customerId; // CustomerID: bắt buộc, phải tồn tại
    private Map<String, Integer> productQuantities; // ProductID -> Quantity (>0)
    private LocalDateTime orderDate; // OrderDate: bắt buộc, định dạng hợp lệ

    // Constructors
    public Order(String id, String customerId, Map<String, Integer> productQuantities, LocalDateTime orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.productQuantities = productQuantities;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<String, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // Override equals và hashCode nếu cần
    // ...
}
