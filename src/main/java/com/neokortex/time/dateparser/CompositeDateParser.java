package com.neokortex.time.dateparser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompositeDateParser {
    private final List<DateParser> dateParsers;

    public CompositeDateParser(List<DateParser> dateParsers) {
        this.dateParsers = dateParsers;
    }

    public List<LocalDate> processString(String input) {
        List<LocalDate> potentialDates = new ArrayList<>();
        String processed = input;
        for (DateParser dp : this.dateParsers) {
            processed = dp.parse(processed, potentialDates);
        }
        return potentialDates;
    }
}
