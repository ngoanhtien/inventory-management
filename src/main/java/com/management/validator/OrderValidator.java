package com.management.validator;

import com.management.model.Order;
import com.management.utils.ErrorLogger;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

public class OrderValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public boolean validate(Order order, ErrorLogger errorLogger, Set<String> existingIds, Set<String> customerIds, Set<String> productIds) {
        boolean isValid = true;
        String id = order.getId();
        String customerId = order.getCustomerId();
        Map<String, Integer> productQuantities = order.getProductQuantities();

        if (id == null || id.isEmpty()) {
            errorLogger.logError("Order ID is required.");
            isValid = false;
        } else if (existingIds.contains(id)) {
            errorLogger.logError("Duplicate Order ID: " + id);
            isValid = false;
        } else {
            existingIds.add(id);
        }

        if (customerId == null || !customerIds.contains(customerId)) {
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

        try {
            if (order.getOrderDate() == null) {
                errorLogger.logError("OrderDate is required in Order ID: " + id);
                isValid = false;
            } else {
                DATE_FORMATTER.parse(order.getOrderDate().toString());
            }
        } catch (DateTimeParseException e) {
            errorLogger.logError("Invalid orderDate format in Order ID: " + id);
            isValid = false;
        }

        return isValid;
    }
}
