package com.management.dataaccess;

import com.management.model.CSVWritable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtil {
    public static <T extends CSVWritable> void writeListToFile(List<T> items, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (T item : items) {
                writer.write(item.toCSVString());
                writer.newLine();
            }
            System.out.println("Data successfully written to " + outputPath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
