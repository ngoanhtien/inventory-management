package com.management.business;

import com.management.dataaccess.DataManager;
import com.management.model.InventoryItem;
import com.management.model.Product;
import com.management.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class BusinessLogic {
    private DataManager dataManager;

    public BusinessLogic(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void handleTransaction(Transaction transaction) {
        // Implement transaction handling for nhập/xuất kho and update InventoryItem
    }

    public List<InventoryItem> getInventorySorted() {
        // Calculate and return sorted inventory quantity
        return new ArrayList<>();
    }

    public List<Transaction> getTopSellingProducts(LocalDate startDate, LocalDate endDate) {
        // Calculate and return products sold most in given time period
        return new ArrayList<>();
    }

    public List<Product> searchProduct(String name, double minPrice, double maxPrice) {
        // Implement product search by name and price range
        return new ArrayList<>();
    }
}
