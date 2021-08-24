package com.coderscampus.CSVParser;

import com.coderscampus.CSVParser.Services.AppServiceImpl;
import com.coderscampus.CSVParser.Services.ConversionServiceImpl;
import com.coderscampus.CSVParser.Services.FileServiceImpl;
import com.coderscampus.CSVParser.interfaces.ConversionService;
import com.coderscampus.CSVParser.interfaces.FileService;

public class CSVParserApp {

    public static void main(String[] args) {

        ConversionService dataConversionService = new ConversionServiceImpl();

        FileService fileService = new FileServiceImpl(dataConversionService);

        AppServiceImpl app = new AppServiceImpl(fileService);

        app.prepareReport();

        app.showReport();

    }
}
