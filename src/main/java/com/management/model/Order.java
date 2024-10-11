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

    // Phương thức xác thực
    public boolean validate(ErrorLogger errorLogger, Set<String> existingIds, Set<String> customerIds, Set<String> productIds) {
        boolean isValid = true;

        if (id == null || id.isEmpty()) {
            errorLogger.logError("Order ID is required.");
            isValid = false;
        } else if (existingIds.contains(id)) {
            errorLogger.logError("Duplicate Order ID: " + id);
            isValid = false;
        } else {
            existingIds.add(id);
        }

        if (customerId == null || customerId.isEmpty() || !customerIds.contains(customerId)) {
            errorLogger.logError("Invalid CustomerID: " + customerId + " in Order ID: " + id);
            isValid = false;
        }

        if (productQuantities == null || productQuantities.isEmpty()) {
            errorLogger.logError("ProductQuantities cannot be empty in Order ID: " + id);
            isValid = false;
        } else {
            for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
                String productId = entry.getKey();
                int quantity = entry.getValue();

                if (!productIds.contains(productId)) {
                    errorLogger.logError("ProductID " + productId + " does not exist in Order ID: " + id);
                    isValid = false;
                }

                if (quantity <= 0) {
                    errorLogger.logError("Quantity for ProductID " + productId + " must be > 0 in Order ID: " + id);
                    isValid = false;
                }
            }
        }

        if (orderDate == null) {
            errorLogger.logError("OrderDate is required in Order ID: " + id);
            isValid = false;
        }

        return isValid;
    }

    // Override equals và hashCode nếu cần
    // ...
}
