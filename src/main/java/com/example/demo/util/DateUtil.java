package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    // Returns the current system date
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    // Returns the current system timestamp for createdAt/lastUpdated fields
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    // Checks if a date is in the future relative to now
    public static boolean isFutureDate(LocalDate date) {
        if (date == null) return false;
        return date.isAfter(LocalDate.now());
    }

    // Helper to calculate the start date of a window (e.g., averageDaysWindow)
    public static LocalDate getDateDaysAgo(int days) {
        return LocalDate.now().minusDays(days);
    }
}