package com.management.dataaccess;

import com.management.model.Product;
import com.management.model.Customer;
import com.management.model.Order;
import com.management.utils.ErrorLogger;
import com.management.validator.CustomerValidator;
import com.management.validator.OrderValidator;
import com.management.validator.ProductValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

//Read and validate data from CSV file
public class DataLoader {
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    private ErrorLogger errorLogger;
    private ProductValidator productValidator = new ProductValidator();
    private CustomerValidator customerValidator = new CustomerValidator();
    private OrderValidator orderValidator = new OrderValidator();

    public DataLoader(ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
    }

    public void loadProducts(String filePath) {
        Set<String> existingIds = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if (columns.length != 4) {
                    errorLogger.logError("Invalid product data format: " + line);
                    continue;
                }

                String id = columns[0].trim();
                String name = columns[1].trim();
                double price;
                int stockAvailable;
                try {
                    price = Double.parseDouble(columns[2].trim());
                } catch (NumberFormatException e) {
                    errorLogger.logError("Invalid price in product ID: " + id);
                    continue;
                }

                try {
                    stockAvailable = Integer.parseInt(columns[3].trim());
                } catch (NumberFormatException e) {
                    errorLogger.logError("Invalid stockAvailable in product ID: " + id);
                    continue;
                }

                Product product = new Product(id, name, price, stockAvailable);
                if (productValidator.validate(product, errorLogger, existingIds)) {
                    products.add(product);
                }
            }
        } catch (IOException e) {
            errorLogger.logError("Error reading products file: " + e.getMessage());
        } finally {
            if (errorLogger != null) {
                errorLogger.close();  // Đảm bảo đóng writer sau khi ghi xong
            }
        }
        FileWriterUtil.writeListToFile(products, "D:\\product.output.csv");
    }

    public void loadCustomers(String filePath) {
        Set<String> existingIds = new HashSet<>();
        Set<String> existingEmails = new HashSet<>();
        Set<String> existingPhones = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if (columns.length != 4) {
                    errorLogger.logError("Invalid customer data format: " + line);
                    continue;
                }

                String id = columns[0].trim();
                String name = columns[1].trim();
                String email = columns[2].trim();
                String phoneNumber = columns[3].trim();

                Customer customer = new Customer(id, name, email, phoneNumber);
                if (customerValidator.validate(customer, errorLogger, existingIds, existingEmails, existingPhones)) {
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            errorLogger.logError("Error reading customers file: " + e.getMessage());
        }
    }

    public void loadOrders(String filePath) {
        Set<String> existingIds = new HashSet<>();
        Set<String> customerIds = new HashSet<>();
        for (Customer customer : customers) {
            customerIds.add(customer.getId());
        }

        Set<String> productIds = new HashSet<>();
        for (Product product : products) {
            productIds.add(product.getId());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length != 4) {
                    errorLogger.logError("Invalid order data format: " + line);
                    continue;
                }

                String id = columns[0].trim();
                String customerId = columns[1].trim();
                String productQuantitiesStr = columns[2].trim();
                String orderDateStr = columns[3].trim();

                Map<String, Integer> productQuantities = parseProductQuantities(productQuantitiesStr, id);
                if (productQuantities == null) continue;

                LocalDateTime orderDate;
                try {
                    orderDate = LocalDateTime.parse(orderDateStr, formatter);
                } catch (DateTimeParseException e) {
                    errorLogger.logError("Invalid orderDate format in Order ID: " + id);
                    continue;
                }

                Order order = new Order(id, customerId, productQuantities, orderDate);
                if (orderValidator.validate(order, errorLogger, existingIds, customerIds, productIds)) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            errorLogger.logError("Error reading orders file: " + e.getMessage());
        }
    }

    private Map<String, Integer> parseProductQuantities(String str, String orderId) {
        Map<String, Integer> productQuantities = new HashMap<>();
        String[] items = str.split(";");

        for (String item : items) {
            String[] pair = item.split(":");
            if (pair.length != 2) {
                errorLogger.logError("Invalid productQuantities format in Order ID: " + orderId);
                return null;
            }

            String productId = pair[0].trim();
            int quantity;
            try {
                quantity = Integer.parseInt(pair[1].trim());
            } catch (NumberFormatException e) {
                errorLogger.logError("Invalid quantity for ProductID " + productId + " in Order ID: " + orderId);
                return null;
            }
            productQuantities.put(productId, quantity);
        }

        return productQuantities;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
