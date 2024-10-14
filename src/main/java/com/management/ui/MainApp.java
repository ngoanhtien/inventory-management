package com.management.ui;

import com.management.dataaccess.DataLoader;
import com.management.utils.ErrorLogger;

public class MainApp {
    public static void main(String[] args) {
        try {
            ErrorLogger errorLogger = new ErrorLogger("error.csv");

            DataLoader dataLoader = new DataLoader(errorLogger);

            dataLoader.loadProducts("products.origin.csv");
            dataLoader.loadCustomers("customers.origin.csv");
            dataLoader.loadOrders("orders.origin.csv");

            // Thực hiện các thao tác khác với dữ liệu đã được tải và xác thực

            errorLogger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
