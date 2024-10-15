package com.management.model;

import com.management.utils.ErrorLogger;

import java.util.Set;

public class Product implements CSVWritable{
    private String id; // Id: bắt buộc, không được trùng
    private String name; // Name: không được để trống
    private double price; // Price: >= 0
    private int stockAvailable; // StockAvailable: >= 0

    // Constructors
    public Product(String id, String name, double price, int stockAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockAvailable = stockAvailable;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    @Override
    public String toCSVString() {
        return String.format("%s,%s,%.2f,%d", id, name, price, stockAvailable);
    }
}

