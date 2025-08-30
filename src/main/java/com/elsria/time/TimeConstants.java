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

    private static final String DAY_REGEX =
            "(?i)" +
                    "(?:\\b(next|following)\\s+)?" +
                    "\\b(sun(?:day)?|mon(?:day)?|" +
                    "tue(?:s|sday)?|wed(?:s|nesday)?|" +
                    "thu(?:r|rs|rsday)?|fri(?:day)?|sat(?:urday)?)\\b";


    private static final String FULL_DATE_REGEX =
            "(?i)"
                    + "\\b(0?[1-9]|[12][0-9]|3[01])?\\s*"                   // Day (1–31, optional leading zero)
                    + "(?:st|nd|rd|th)?\\s*"                         // Optional ordinal suffix
                    + "\\b(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|"      // Month (short/full)
                    + "apr(?:il)?|may|jun(?:e)?|jul(?:y)?|"
                    + "aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|"
                    + "nov(?:ember)?|dec(?:ember)?)\\b\\s*"
                    + "((?:\\d{2})?\\d{2})?\\b";                        // Optional 2- or 4-digit year

    private static final String SIMPLE_DATE_REGEX =
            "(?i)" +
                    "\\b(0?[1-9]|[12][0-9]|3[01])" +                    // Day (1–31, optional leading zero)
                    "\\s*[\\\\/]\\s*" +                              // Slash or backslash with optional spaces
                    "(0?[1-9]|1[0-2])" +                             // Month (1-12, optional leading zero)
                    "(?:\\s*[\\\\/]\\s*" +                              // Slash or backslash with optional spaces
                    "((?:\\d{2})?\\d{2}))?\\b";                         // Optional 2- or 4-digit year

    public static final Set<String> DATE_PHRASES = Set.of(
            "tomorrow", "today", "yesterday", "next week", "next month", "next year"
    );

    public static final Pattern TWELVE_HOUR_PATTERN = Pattern.compile(TWELVE_HOUR_REGEX, Pattern.CASE_INSENSITIVE);
    public static final Pattern TWENTY_FOUR_HOUR_PATTERN = Pattern.compile(TWENTY_FOUR_HOUR_REGEX, Pattern.CASE_INSENSITIVE);
    public static final Pattern DAY_PATTERN = Pattern.compile(DAY_REGEX, Pattern.CASE_INSENSITIVE);
    public static final Pattern FULL_DATE_PATTERN = Pattern.compile(FULL_DATE_REGEX, Pattern.CASE_INSENSITIVE);
    public static final Pattern SIMPLE_DATE_PATTERN = Pattern.compile(SIMPLE_DATE_REGEX, Pattern.CASE_INSENSITIVE);

    private TimeConstants() {}

}
