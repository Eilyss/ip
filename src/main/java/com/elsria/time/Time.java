package com.elsria.time;

import com.elsria.time.dateparser.CompositeDateParser;
import com.elsria.time.dateparser.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Time {
    public static LocalDateTime convertToTime(String input) {
        LocalDateTime currentTime = LocalDateTime.now();
        CompositeDateParser dateParser = DateParser.createDefaultParser();
        List<LocalDate> possibleDates = dateParser.processString(input);

        if (possibleDates.isEmpty()) {
            return null;
        }
        LocalDateTime result = LocalDateTime.of(possibleDates.getLast(), LocalTime.now());
        return result;
    }

}
