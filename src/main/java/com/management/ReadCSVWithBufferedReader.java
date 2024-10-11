package com.management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVWithBufferedReader {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\tien\\Downloads\\Book1.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

