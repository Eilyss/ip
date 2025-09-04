package com.elsria.time.dateparser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompositeDateParser {
    private final List<DateParser> dateParsers;

    public CompositeDateParser(List<DateParser> dateParsers) {
        this.dateParsers = dateParsers;
    }

    public List<LocalDate> processString(String input) {
        List<LocalDate> dates = new ArrayList<>();
        for (DateParser dp : this.dateParsers) {
            dates.addAll(dp.parse(input));
        }
        return dates;
    }
}
