package com.coderscampus.CSVParser.Services;

import com.coderscampus.CSVParser.interfaces.FileService;

import java.io.File;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class AppServiceImpl {

    FileService fileService;

    public AppServiceImpl(FileService fileService){
        this.fileService = fileService;
    }

    public void prepareReport(){
        Map<String,File> files = new HashMap<>();

        files.put("Model 3",new File("data/model3.csv"));
        files.put("Model S",new File("data/modelS.csv"));
        files.put("Model X",new File("data/modelX.csv"));

        this.fileService.readFiles(files);
    }

    public void showReport(){
        this.fileService.getSalesDataSet().forEach((reportName,dataset)->{

            System.out.println(reportName + " Yearly Sales Report");
            System.out.println("---------------------------");

            // groups data by year
            Map<Integer, List<Map.Entry<YearMonth, Integer>>> groupedDataSet = dataset
                    .entrySet()
                    .stream()
                    .collect(Collectors.groupingBy(data -> data.getKey().getYear()));

            // sums sales for all years
            groupedDataSet.forEach((year,salesData) -> {
                IntSummaryStatistics summary = salesData.stream().collect(Collectors.summarizingInt(Map.Entry::getValue));
                System.out.println(year + " -> " + summary.getSum());
            });

            // gets the date for the best year
            Optional<Map.Entry<YearMonth, Integer>> maxSales = dataset.entrySet().stream().max(Map.Entry.comparingByValue());

            // gets the date for the best year
            Optional<Map.Entry<YearMonth, Integer>> minSales = dataset.entrySet().stream().min(Map.Entry.comparingByValue());

            if(maxSales.isPresent() && minSales.isPresent()){
                YearMonth maxSaleDate = YearMonth.of(maxSales.get().getKey().getYear(),maxSales.get().getKey().getMonth());
                YearMonth minSaleDate = YearMonth.of(minSales.get().getKey().getYear(),minSales.get().getKey().getMonth());
                System.out.println("The best month for " + reportName + " was: " + maxSaleDate);
                System.out.println("The worst month for " + reportName + "  was: " + minSaleDate);
                System.out.println();
            }else{
                System.out.println("There are no best and/or worst year data are available!");
            }
        });
    }
}
