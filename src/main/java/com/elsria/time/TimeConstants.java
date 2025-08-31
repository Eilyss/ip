package com.elsria.time;

import java.util.Set;
import java.util.regex.Pattern;

public class TimeConstants {
    private static final String TWELVE_HOUR_REGEX =
            "(?i)"
                    + "(0?[1-9]|1[0-2])"            // 12-hour format hours (1-12 with optional leading zero)
                    + "(?:[.:]([0-5]\\d))?"         // optional minutes
                    + "(?:[.:]([0-5]\\d))?"         // optional seconds
                    + "\\s*([ap])\\.?[m]\\.?";      // am/pm with optional dots, optional whitespace before

    private static final String TWENTY_FOUR_HOUR_REGEX =
            "(?i)"
                    + "([01]?\\d|2[0-3])"           // 24-hour format hours (0-23)
                    + "(?:[.:]([0-5]\\d))?";        // optional minutes

    public static final Set<String> DAYS = Set.of(
            "sun", "sunday",
            "mon", "monday",
            "tue", "tues", "tuesday",
            "wed", "weds", "wednesday",
            "thu", "thur", "thurs", "thursday",
            "fri", "friday",
            "sat", "saturday"
    );

    public static final Set<String> DATE_PHRASES = Set.of(
            "tomorrow", "today", "yesterday", "next week", "next month", "next year"
    );

    public static final Pattern TWELVE_HOUR_PATTERN = Pattern.compile(TWELVE_HOUR_REGEX, Pattern.CASE_INSENSITIVE);
    public static final Pattern TWENTY_FOUR_HOUR_PATTERN = Pattern.compile(TWENTY_FOUR_HOUR_REGEX, Pattern.CASE_INSENSITIVE);

    private TimeConstants() {}

}
