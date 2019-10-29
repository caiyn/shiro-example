package com.example.shiroexample.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String YMD_HMS_FORMATTER_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_FORMATTER_STR = "yyyy-MM-dd";
    public static final String YMD_HMS_HYPHEN_FORMATTER_STR = "yyyy-MM-dd-HH-mm-ss";
    public static final String YMD_HYPHEN_FORMATTER_STR = "yyyy-MM-dd";
    public static final DateTimeFormatter YMD_HMS_FORMATTER = DateTimeFormatter.ofPattern(YMD_HMS_FORMATTER_STR);
    public static final DateTimeFormatter YMD_HYPHEN_FORMATTER = DateTimeFormatter.ofPattern(YMD_HYPHEN_FORMATTER_STR);
    public static final DateTimeFormatter YMD_HMS_HYPHEN_FORMATTER = DateTimeFormatter.ofPattern(YMD_HMS_HYPHEN_FORMATTER_STR);
    public static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern(YMD_FORMATTER_STR);

    public static String format(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(YMD_HMS_FORMATTER);
    }

    public static String nowStr() {
        return LocalDateTime.now().format(YMD_HMS_FORMATTER);
    }

    public static String nowDateHyphenStr() {
        return LocalDateTime.now().format(YMD_HYPHEN_FORMATTER);
    }

    public static String nowHyphenStr() {
        return LocalDateTime.now().format(YMD_HMS_HYPHEN_FORMATTER);
    }

    public static LocalDateTime parse(String date, DateTimeFormatter formatter) {
        return LocalDateTime.parse(date, formatter);
    }
}
