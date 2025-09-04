package com.elsria.time.dateparser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDateParser extends DateParser {
    private static final String SIMPLE_DATE_REGEX =
            "(?i)"
                    + "\\b(0?[1-9]|[12][0-9]|3[01])"
                    + "\\s*[\\\\/\\-]\\s*"
                    + "(0?[1-9]|1[0-2])"
                    + "(?:\\s*[\\\\/\\-]\\s*"
                    + "((?:\\d{2})?\\d{2}))?\\b";

    private static final Pattern pattern =
            Pattern.compile(SIMPLE_DATE_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public List<LocalDate> parse(String date) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(date);
        ArrayList<LocalDate> potentialDates = new ArrayList<>();
        boolean error = false;

        while (dateMatcher.find()) {
            int day = Integer.parseInt(dateMatcher.group(1));
            int month = Integer.parseInt(dateMatcher.group(2));
            int year = dateMatcher.group(3) == null
                       ? currentDate.getYear()
                       : Integer.parseInt(dateMatcher.group(3));

            year = (year < 100) ? 2000 + year : year;

            try {
                potentialDates.add(LocalDate.of(year, month, day));
            } catch (DateTimeException ignored) {
                error = true;
            }

        }
        return potentialDates;
    }
}
