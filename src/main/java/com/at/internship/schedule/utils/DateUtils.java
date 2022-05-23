package com.at.internship.schedule.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
    private static final String FMT_DATETIME_DEFAULT = "yyyy-MM-dd";
    private static final String FMT_DATE_DEFAULT = "MM/dd/yyyy";

    public String formatDefault(LocalDateTime dateTime) {
        if(dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern(FMT_DATETIME_DEFAULT));
    }

    public String formatDefault(LocalDate date) {
        if(date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(FMT_DATE_DEFAULT));
    }

    public LocalDate parseDefaultDate(String date) {
        if(date == null) return null;
        return LocalDate.parse(date,DateTimeFormatter.ofPattern(FMT_DATE_DEFAULT));
    }

    public LocalDateTime parseDefaultDateTime(String datetime) {
        if(datetime == null) return null;
        return LocalDateTime.parse(datetime,DateTimeFormatter.ofPattern(FMT_DATETIME_DEFAULT));
    }
}
