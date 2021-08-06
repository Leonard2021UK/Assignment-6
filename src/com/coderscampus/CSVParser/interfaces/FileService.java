package com.coderscampus.CSVParser.interfaces;

import java.io.File;
import java.util.Map;

public interface FileService {
    void fileRead(Map<String, File> filePaths);

    void readDataFromFile(Map.Entry<String,File> file);

}
