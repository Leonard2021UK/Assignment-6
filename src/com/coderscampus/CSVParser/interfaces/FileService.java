package com.coderscampus.CSVParser.interfaces;

import java.io.File;
import java.util.Map;

public interface FileService {
    void fileRead(Map<String, File> filePaths);

    private void readDataFromFile(Map.Entry<String,String> file) {

    }

}
