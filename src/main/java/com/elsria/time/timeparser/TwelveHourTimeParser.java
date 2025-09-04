package com.elsria.time.timeparser;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwelveHourTimeParser extends TimeParser {
    private static final String TWELVE_HOUR_REGEX =
            "(?i)"
                    + "(0?[1-9]|1[0-2])"
                    + "(?:[.:]([0-5]\\d))?"
                    + "(?:[.:]([0-5]\\d))?"
                    + "\\s*([ap])\\.?m\\.?";

    private static final Pattern pattern = Pattern.compile(TWELVE_HOUR_REGEX, Pattern.CASE_INSENSITIVE);


    @Override
    public List<LocalTime> parse(String date) {
        Matcher matcher = pattern.matcher(date);
        ArrayList<LocalTime> times = new ArrayList<LocalTime>();
        boolean invalid = false;

        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = matcher.group(2) == null ? 0 : Integer.parseInt(matcher.group(2));
            int second = matcher.group(3) == null ? 0 : Integer.parseInt(matcher.group(3));

            String meridiem = matcher.group(4);

            if (meridiem.matches("p\\.?m") && hour != 12) {
                hour += 12;
            } else if (meridiem.matches("a\\.?m") && hour == 12) {
                hour = 0;
            }

            try {
                times.add(LocalTime.of(hour, minute, second));
            } catch (DateTimeException e) {
                invalid = true;
            }
        }

        return times;
    }
}
