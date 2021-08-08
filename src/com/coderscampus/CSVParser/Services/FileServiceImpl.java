package com.coderscampus.CSVParser.Services;

import com.coderscampus.CSVParser.interfaces.ConversionService;
import com.coderscampus.CSVParser.interfaces.FileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class FileServiceImpl implements FileService {

    // holds data read from the file
    private final Map<String, Map<LocalDate,Integer>> salesDataSet = new HashMap<>();

    // provides conversion functionalities
    private final ConversionService conversionService;
    
    public FileServiceImpl(ConversionService conversionService){
        this.conversionService = conversionService;
    }

    /**
     * Iterates through all the files provided for parsing
     */
    @Override
    public void readFile(Map<String,File> filePaths){

        for (Map.Entry<String,File> entry: filePaths.entrySet()){

            // clear Deque
            this.conversionService.resetReverseData();

            readAndStoreDataInReverse(entry);

            // stores the whole dataset read from the file in a map data structure
            String reportName = entry.getKey();
            this.salesDataSet.put(reportName,this.conversionService.convertData());
        }
    }

    @Override
    public Map<String, Map<LocalDate, Integer>> getSalesDataSet() {
        return salesDataSet;
    }

    /**
     * Reads and stores the data into Deque to maintain LIFO
     * @param file - file to read
     */
    @Override
    public void readAndStoreDataInReverse(Map.Entry<String, File> file){

        // It uses try-with-resources where the resource "BufferedReader" is automatically closed once finished (normally or abruptly),
        // hence no need for finally block.
        try (BufferedReader br = new BufferedReader(new FileReader(file.getValue()))) {

            // holds data read from the file
            String csvData;

            // iterate through the file
            while ((csvData = br.readLine()) != null){
                this.conversionService.setReverseData(csvData);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }
    }
}

