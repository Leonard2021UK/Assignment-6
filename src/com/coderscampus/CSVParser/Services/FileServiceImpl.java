package com.coderscampus.CSVParser.Services;

import com.coderscampus.CSVParser.interfaces.FileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class FileServiceImpl implements FileService {

    // holds data read from the file
    private final Map<String,Map<String,String>> salesDataSet = new TreeMap<>();

    /**
     * Reads file into an ArrayList
     */
    @Override
    public void fileRead(Map<String,File> filePaths){


        for (Map.Entry<String,File> entry: filePaths.entrySet()){
            readDataFromFile(entry);
        }

        System.out.println(getSalesDataSetByName("modelSFile").toString());

    }


    private void readDataFromFile(Map.Entry<String,File> file){
        // It uses try-with-resources where the resource "BufferedReader" is automatically closed once finished (normally or abruptly),
        // hence no need for finally block.
        try (BufferedReader br = new BufferedReader(new FileReader(file.getValue()))) {
            String csvData;
            Map<String,String> parsedData = new TreeMap<>();

            while ((csvData = br.readLine()) != null){
                System.out.println(csvData);
                String[] salesData = csvData.split(",");
                String date = salesData[0];
                String sale = salesData[1];
                parsedData.put(date,sale);
            }

            this.salesDataSet.put(file.getKey(),parsedData);

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    public Map<String, Map<String, String>> getSalesDataSet() {
        return salesDataSet;
    }

    public Map<String, String> getSalesDataSetByName(String name) {
        return salesDataSet.get(name);
    }

}

