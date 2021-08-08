package com.coderscampus.CSVParser.interfaces;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

public interface FileService {

    void readFile(Map<String, File> filePaths);

    void readAndStoreDataInReverse(Map.Entry<String, File> file);

    Map<String, Map<LocalDate, Integer>> getSalesDataSet();

}
