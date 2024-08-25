package com.temperature.temperature.utils;

import java.time.LocalDateTime;

public class DateTimeUtils {    public static LocalDateTime roundToNearestTenMinutes(LocalDateTime dateTime) {
    int minute = dateTime.getMinute();
    int remainder = minute % 10;

    if (remainder < 5) {
        dateTime = dateTime.minusMinutes(remainder);
    } else {
        dateTime = dateTime.plusMinutes(10 - remainder);
    }

    dateTime = dateTime.withSecond(0).withNano(0);

    return dateTime;
}
}
