package com.elsria.time.timeparser;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwentyFourHourTimeParser extends TimeParser {
    private static final String TWENTY_FOUR_HOUR_REGEX =
            "(?i)"
                    + "([01]\\d|2[0-3])"             // 24-hour format hours (0-23)
                    + "[.:]?([0-5]\\d)";             // Minutes

    public static final Pattern pattern = Pattern.compile(TWENTY_FOUR_HOUR_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public List<LocalTime> parse(String date) {
        Matcher matcher = pattern.matcher(date);
        ArrayList<LocalTime> times = new ArrayList<LocalTime>();

        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = matcher.group(2) == null ? 0 : Integer.parseInt(matcher.group(2));

            try {
                times.add(LocalTime.of(hour, minute));
            } catch (DateTimeException ignore) {

            }
        }

        return times;
    }
}
