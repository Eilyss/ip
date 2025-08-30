package com.elsria.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Time {
    public static LocalDateTime convertToTime(String input) {
        LocalDateTime currentTime = LocalDateTime.now();
        final Set<Function<String, List<LocalDate>>> dateParser = DateParser.parseFunctions;
        ArrayList<LocalDate> possibleDates = new ArrayList<>();

        for (Function<String, List<LocalDate>> parser : dateParser) {
            possibleDates.addAll(parser.apply(input));
        }

        if (possibleDates.isEmpty()) {
            return null;
        }
        LocalDateTime result = LocalDateTime.of(possibleDates.getLast(), LocalTime.now());
        return result;
    }

}
