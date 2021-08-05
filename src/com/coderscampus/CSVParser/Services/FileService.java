package com.coderscampus.CSVParser.Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    // holds data read from the file
    private final List<String> dataArray = new ArrayList<>();

    /**
     * Reads file into an ArrayList
     */
    public void readFile(){
        // Store file path
        File model3File = new File("data/model3.csv");
        File modelSFile = new File("data/modelS.csv");
        File modelXFile = new File("data/modelX.csv");

        // It uses try-with-resources where the resource "BufferedReader" is automatically closed once finished (normally or abruptly),
        // hence no need for finally block.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String userData;
            while ((userData = br.readLine()) != null){
                System.out.println(userData);
                dataArray.add(userData);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    public List<String> getDataArray() {
        return dataArray;
    }
}

