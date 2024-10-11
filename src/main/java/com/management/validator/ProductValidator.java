package com.management.validator;

import com.management.model.Product;
import com.management.utils.ErrorLogger;
import java.util.Set;

public class ProductValidator {
    public boolean validate(Product product, ErrorLogger errorLogger, Set<String> existingIds) {
        boolean isValid = true;
        String id = product.getId();
        String name = product.getName();

        if (id == null || id.isEmpty()) {
            errorLogger.logError("Product ID is required.");
            isValid = false;
        } else if (existingIds.contains(id)) {
            errorLogger.logError("Duplicate Product ID: " + id);
            isValid = false;
        } else {
            existingIds.add(id);
        }

        if (name == null || name.isEmpty()) {
            errorLogger.logError("Product Name cannot be empty. ID: " + id);
            isValid = false;
        }

        if (product.getPrice() < 0) {
            errorLogger.logError("Product Price must be >= 0. ID: " + id);
            isValid = false;
        }

        if (product.getStockAvailable() < 0) {
            errorLogger.logError("StockAvailable must be >= 0. ID: " + id);
            isValid = false;
        }

        return isValid;
    }
}
