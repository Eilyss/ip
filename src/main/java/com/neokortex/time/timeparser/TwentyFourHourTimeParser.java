package com.neokortex.time.timeparser;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwentyFourHourTimeParser extends TimeParser {
    private static final String TWENTY_FOUR_HOUR_REGEX =
            "(?i)"
                    + "([01]\\d|2[0-3])"
                    + "[.:]?([0-5]\\d)";

    private static final Pattern pattern =
            Pattern.compile(TWENTY_FOUR_HOUR_REGEX,
                    Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String date, List<? super LocalTime> potentialTimes) {
        Matcher matcher = pattern.matcher(date);
        boolean error = false;

        StringBuffer strippedInput = new StringBuffer();

        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = matcher.group(2) == null
                    ? 0
                    : Integer.parseInt(matcher.group(2));

            try {
                potentialTimes.add(LocalTime.of(hour, minute));
                matcher.appendReplacement(strippedInput, "");
            } catch (DateTimeException ignore) {
                error = true;
            }
        }

        matcher.appendTail(strippedInput);

        return strippedInput.toString();
    }
}
