package com.at.internship.schedule.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private final String defaultDateTimeFormat;
    private final String defaultDateFormat;

    public DateUtils(String defaultDateTimeFormat, String defaultDateFormat) {
        this.defaultDateTimeFormat = defaultDateTimeFormat;
        this.defaultDateFormat = defaultDateFormat;
    }

    public String formatDefault(LocalDateTime dateTime) {
        if(dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern(defaultDateTimeFormat));
    }

    public String formatDefault(LocalDate date) {
        if(date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(defaultDateFormat));
    }

    public LocalDate parseDefaultDate(String date) {
        if(date == null) return null;
        return LocalDate.parse(date,DateTimeFormatter.ofPattern(defaultDateFormat));
    }

    public LocalDateTime parseDefaultDateTime(String datetime) {
        if(datetime == null) return null;
        return LocalDateTime.parse(datetime,DateTimeFormatter.ofPattern(defaultDateTimeFormat));
    }
}
