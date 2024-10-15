package com.management.ui;

import com.management.dataaccess.DataLoader;
import com.management.utils.ErrorLogger;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        try {
            ErrorLogger er = new ErrorLogger("D:\\error.output.csv");
            DataLoader dataLoader = new DataLoader(er);
            dataLoader.loadProducts("D:\\product.origin.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
