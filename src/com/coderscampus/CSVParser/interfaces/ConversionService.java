package com.coderscampus.CSVParser.interfaces;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public interface ConversionService {

    Optional<LocalDate> stringToDate(String date);

    void initializePreviousLocalDate ();

    Map<LocalDate,Integer> convertData();

    void setReverseData(String data);

    void resetReverseData();

}
