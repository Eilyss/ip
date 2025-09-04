package com.elsria.time.timeparser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CompositeTimeParser {
    private final List<TimeParser> timeParsers;

    public CompositeTimeParser(List<TimeParser> timeParsers) {
        this.timeParsers = timeParsers;
    }

    public List<LocalTime> processString(String input) {
        List<LocalTime> times = new ArrayList<>();
        for (TimeParser tp : this.timeParsers) {
            times.addAll(tp.parse(input));
        }
        return times;
    }
}
