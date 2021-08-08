package com.coderscampus.CSVParser.Services;

import com.coderscampus.CSVParser.interfaces.ConversionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.regex.Pattern;

public class ConversionServiceImpl implements ConversionService {

    // the data is processed backwards from 2019
    // this is the starting point in all three reports
    private final LocalDate initialDate;

    // this holds the previous sales date
    private LocalDate previousLocalDate;

    // stores data in a Deque which maintains LIFO
    private final Deque<String> reverseData;

    // regex to match numeric Strings consisting of the positive or negative integer and floats.
    // ref.:https://www.baeldung.com/java-check-string-number
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public ConversionServiceImpl(Integer latestYearInReport){
        this.initialDate = LocalDate.of(latestYearInReport,12,31);
        this.reverseData = new ArrayDeque<>();
    }

    // adds item in to a Deque datastructures thus maintaining LIFO
    public void setReverseData(String data){
        this.reverseData.push(data);
    }

    //resets the Deque when new file is starting to be read
    public void resetReverseData(){
        this.reverseData.clear();
    }

    public void initializePreviousLocalDate (){
        this.previousLocalDate = initialDate;
    }

    // sets the next year (backwards)
    private void startNewYear(){
        this.previousLocalDate = this.previousLocalDate.minusYears(1).withMonth(12).withDayOfMonth(31);
    }

    /**
     * Converts date string to LocalDate
     * @param date - date string to be parsed
     * @return an optional localDate
     */
    @Override
    public Optional<LocalDate> stringToDate(String date) {

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd");
            LocalDate currentLocalDateTime = MonthDay.parse(date,formatter).atYear(this.previousLocalDate.getYear());

            // whenever the current date is after the previous date it means it is a new year's sales data
            if(currentLocalDateTime.isBefore(previousLocalDate)){
                previousLocalDate = currentLocalDateTime;
            }else{
                currentLocalDateTime = currentLocalDateTime.minusYears(1);
                startNewYear();
            }
            return Optional.of(currentLocalDateTime);

        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * Reads the CSV file and stores the data
     */
    public Map<LocalDate,Integer> convertData(){

        try{
            // stores the parsed file data converted time
            Map<LocalDate,Integer> parsedData = new TreeMap<>();

            initializePreviousLocalDate();

            this.reverseData.forEach(csvLine -> {

                // split the line of string read from the csv file
                String[] salesData = csvLine.split(",");
                String date = salesData[0];
                Optional<Integer> sale = isNumeric(salesData[1]) ? Optional.of(Integer.parseInt(salesData[1])) : Optional.empty();

                // convert date string to optional local date
                Optional<LocalDate> saleDate = stringToDate(date);

                // check whether the conversion was successfully carried out
                // if the date String and the numeric String was valid then store them
                if (saleDate.isPresent() && sale.isPresent()){
                    parsedData.put(saleDate.get(), sale.get());
                }
            });

            return parsedData;

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
            return null;
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
