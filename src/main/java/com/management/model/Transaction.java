package com.management.model;

import java.time.LocalDate;

public class Transaction {
    private String transactionId;
    private String productId;
    private String inventoryId;
    private int quantity;
    private LocalDate date;
    private boolean isPurchase; // nhập kho: true, xuất kho: false

}
