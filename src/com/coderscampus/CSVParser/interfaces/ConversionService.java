package com.coderscampus.CSVParser.interfaces;

import java.time.YearMonth;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

public interface ConversionService {

    Optional<YearMonth> stringToDate(String date);

    void parseSalesData(String csvData);

    void resetParsedData();

    Map<YearMonth,Integer> getParsedData();

}
