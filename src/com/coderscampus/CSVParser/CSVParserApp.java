package com.coderscampus.CSVParser;

import com.coderscampus.CSVParser.Services.AppServiceImpl;
import com.coderscampus.CSVParser.Services.ConversionServiceImpl;
import com.coderscampus.CSVParser.Services.FileServiceImpl;
import com.coderscampus.CSVParser.interfaces.ConversionService;
import com.coderscampus.CSVParser.interfaces.FileService;

public class CSVParserApp {

    public static void main(String[] args) {

        // all reports requires to have its latest year to be the same (in this case it is 2019)
        // it is the base date, used to determine all required years in the report
        // the data is parsed in reverse order
        // this is the only date which has to be hardcoded
        ConversionService dtcs = new ConversionServiceImpl(2019);

        FileService fs = new FileServiceImpl(dtcs);

        AppServiceImpl app = new AppServiceImpl(fs);

        app.prepareReport();

        app.showReport();

    }
}
