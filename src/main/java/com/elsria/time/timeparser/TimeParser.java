package com.elsria.time.timeparser;

import java.time.LocalTime;
import java.util.List;

public abstract class TimeParser {
    public static CompositeTimeParser createDefaultParser() {
        return new CompositeTimeParser(List.of(
                new TwelveHourTimeParser(),
                new TwentyFourHourTimeParser()
        ));
    }

    public abstract List<LocalTime> parse(String date);
}
