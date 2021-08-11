package com.coderscampus.CSVParser.interfaces;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import java.util.Optional;

public interface ConversionService {

    Optional<YearMonth> stringToDate(String date);

//    void initializePreviousLocalDate ();

    void convertSalesData(String csvData);

    void setSalesData(String data);

    void resetSalesData();

    Map<YearMonth,Integer> getParsedData();

}
