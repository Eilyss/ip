package com.neokortex.time.timeparser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CompositeTimeParser {
    private final List<TimeParser> timeParsers;

    public CompositeTimeParser(List<TimeParser> timeParsers) {
        this.timeParsers = timeParsers;
    }

    public List<LocalTime> processString(String input) {
        List<LocalTime> potentialTimes = new ArrayList<>();
        String processed = input;
        for (TimeParser tp : this.timeParsers) {
            processed = tp.parse(processed, potentialTimes);
        }
        return potentialTimes;
    }
}
