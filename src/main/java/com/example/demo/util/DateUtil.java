package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    private DateUtil() {
        // utility class
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
