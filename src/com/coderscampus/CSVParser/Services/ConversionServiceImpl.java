package com.coderscampus.CSVParser.Services;

import com.coderscampus.CSVParser.interfaces.ConversionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.regex.Pattern;

public class ConversionServiceImpl implements ConversionService {


    // this holds the previous sales date
    private YearMonth previousYearMonth = YearMonth.of(1900,1);

    // stores data in a Deque which maintains LIFO
    private final Map<MonthDay,Integer> salesData;


    // stores the parsed data
    private Map<YearMonth,Integer> parsedData = new TreeMap<>();

    // regex to match numeric Strings consisting of the positive or negative integer and floats.
    // ref.:https://www.baeldung.com/java-check-string-number
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public ConversionServiceImpl(){
        this.salesData = new HashMap<>();

    }

    // adds item in to a Deque datastructures thus maintaining LIFO
//    public void setReverseData(String data){
//        this.reverseData.push(data);
//    }
//    public void setSalesData(String data){
//
//        this.salesData.put(data);
//    }

    //resets sales data when new file is starting
    public void resetSalesData(){
        this.salesData.clear();
    }

//    public void initializePreviousLocalDate (){
//        this.previousYearMonth = initialDate;
//    }

    // sets the next year (backwards)
//    private void startNewYear(){
//        this.previousYearMonth = this.previousYearMonth.minusYears(1).withMonth(12).withDayOfMonth(31);
//    }

    private void startNewYear(){
        this.previousYearMonth = this.previousYearMonth.plusYears(1).withMonth(12);
    }

    public Map<YearMonth, Integer> getParsedData() {
        return this.parsedData;
    }

    /**
     * Converts date string to LocalDate
     * @param date - date string to be parsed
     * @return an optional localDate
     */
    @Override
    public Optional<YearMonth> stringToDate(String date) {

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-uu");

            YearMonth currentYearMonth = YearMonth.parse(date,formatter);

            return Optional.of(currentYearMonth);

        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * Reads the CSV file and stores the data
     */
    public void convertSalesData(String csvData){

        String[] splitSalesData = csvData.split(",");
        String date = splitSalesData[0];
        String sales = splitSalesData[1];

        Optional<YearMonth> yearMonth = stringToDate(date);
        Optional<Integer> sale = isNumeric(sales) ? Optional.of(Integer.parseInt(sales)) : Optional.empty();

        try{
            // check whether the conversion was successfully carried out
            // if the date String and the numeric String was valid then store them
            if (yearMonth.isPresent() && sale.isPresent()){
                this.parsedData.put(yearMonth.get(), sale.get());
            }


        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param strNum - String to check
     * @return true if the input can be parsed to Integer
     */
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
