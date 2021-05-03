package com.atividadeavaliativa.employeemanagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatarData {

    private static FormatarData instance = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private FormatarData() {
    }

    public static FormatarData getInstance() {
        if (instance == null) {
            instance = new FormatarData();
        }
        return instance;
    }

    public static LocalDate parseStringToLocalDate(String date) throws ParseException, Exception {
        LocalDate localdate = LocalDate.parse(date, formatter);
        if(localdate.isAfter(LocalDate.now())){
            throw new Exception("A data não pode ser superior a data atual!");
        }
        
        return localdate;
    }

    public static String parseLocalDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(formatter);
    }


    public static String parseDateToString(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Selecione uma data para realizar o Calculo!");
        }
        return sdf.format(date);
    }
}
