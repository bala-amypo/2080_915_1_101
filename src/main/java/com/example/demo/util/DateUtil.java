package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil { // [cite: 14]
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}