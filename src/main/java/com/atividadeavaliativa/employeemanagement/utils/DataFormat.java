package com.atividadeavaliativa.employeemanagement.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataFormat {

    private static DataFormat instance = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    private DataFormat() {
    }

    public static DataFormat getInstance() {
        if (instance == null) {
            instance = new DataFormat();
        }
        return instance;
    }

    public static LocalDate parseStringToLocalDate(String date) throws ParseException {
        LocalDate localdate = LocalDate.parse(date, formatter);
        return localdate;
    }
    

    public static String parseLocalDateToString(LocalDate date) {
   if (date == null) {
         return null;
     }
      return date.format(formatter);
  }


}
