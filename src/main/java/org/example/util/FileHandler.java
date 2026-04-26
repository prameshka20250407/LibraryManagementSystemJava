package org.example.util;

import java.io.*;
import java.util.*;

public class FileHandler {

    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    public static void writeCSV(String filePath, List<String[]> data) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (String[] row : data) {
                writer.println(String.join(",", row));
            }
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
