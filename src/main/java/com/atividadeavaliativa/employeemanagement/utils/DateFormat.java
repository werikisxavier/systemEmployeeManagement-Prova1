package com.atividadeavaliativa.employeemanagement.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    private static DateFormat instance = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    private DateFormat() {
    }

    public static DateFormat getInstance() {
        if (instance == null) {
            instance = new DateFormat();
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
