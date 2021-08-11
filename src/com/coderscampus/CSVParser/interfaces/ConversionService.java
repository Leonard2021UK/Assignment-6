package com.coderscampus.CSVParser.interfaces;

import java.time.YearMonth;
import java.util.Map;
import java.util.Optional;

public interface ConversionService {

    Optional<YearMonth> stringToDate(String date);

    void convertSalesData(String csvData);

    void resetParsedData();

    Map<YearMonth,Integer> getParsedData();

}
