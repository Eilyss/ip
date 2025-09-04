package com.elsria.time.dateparser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.elsria.time.Month;

public class FullDateParser extends DateParser {
    private static final String FULL_DATE_REGEX =
            "(?i)"
                    + "\\b(0?[1-9]|[12][0-9]|3[01])\\s*"
                    + "(?:st|nd|rd|th)?\\s*"
                    + "\\b(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|"
                    + "apr(?:il)?|may|jun(?:e)?|jul(?:y)?|"
                    + "aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|"
                    + "nov(?:ember)?|dec(?:ember)?)\\b\\s*"
                    + "(\\d{4})?\\b";

    private static final Pattern pattern =
            Pattern.compile(FULL_DATE_REGEX,
                    Pattern.CASE_INSENSITIVE);

    @Override
    public List<LocalDate> parse(String date) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(date);
        ArrayList<LocalDate> potentialDates = new ArrayList<>();
        boolean error = false;

        while (dateMatcher.find()) {
            int day = dateMatcher.group(1) == null
                      ? 1
                      : Integer.parseInt(dateMatcher.group(1));
            int month = Month.fromAlias(dateMatcher.group(2))
                    .map(Month::getAsNumber)
                    .orElse(currentDate.getMonthValue());
            int year = dateMatcher.group(3) == null
                       ? currentDate.getYear()
                       : Integer.parseInt(dateMatcher.group(3));
            year += (year < 100) ? 2000 : 0;

            try {
                potentialDates.add(LocalDate.of(year, month, day));
            } catch (DateTimeException ignored) {
                error = true;
            }
        }
        return potentialDates;
    }
}
