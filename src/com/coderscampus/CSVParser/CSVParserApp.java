package com.coderscampus.CSVParser;

import com.coderscampus.CSVParser.Services.FileServiceImpl;
import com.coderscampus.CSVParser.interfaces.FileService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CSVParserApp {
    public static void main(String[] args) {

        FileService fs = new FileServiceImpl();

        // Store file path
        File model3File = new File("data/model3.csv");
        File modelSFile = new File("data/modelS.csv");
        File modelXFile = new File("data/modelX.csv");

        Map<String,File> files = new HashMap<>();

        files.put("model3File",new File("data/model3.csv"));
        files.put("modelSFile",new File("data/modelS.csv"));
        files.put("modelXFile",new File("data/modelX.csv"));

        fs.fileRead(files);

    }
}
