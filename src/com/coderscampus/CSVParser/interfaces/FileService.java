package com.coderscampus.CSVParser.interfaces;

import java.io.File;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

public interface FileService {

    void readFiles(Map<String, File> filePaths);

    void readData(Map.Entry<String, File> file);

    Map<String, Map<YearMonth, Integer>> getSalesDataSet();

}
