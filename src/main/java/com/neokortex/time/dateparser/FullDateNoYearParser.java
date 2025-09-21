package com.neokortex.time.dateparser;

import com.neokortex.time.Month;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullDateNoYearParser extends DateParser {
    private static final String FULL_DATE_REGEX =
            "(?i)"
                    + "\\b(0?[1-9]|[12][0-9]|3[01])"
                    + "(?:st|nd|rd|th)?\\s*"
                    + "(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|"
                    + "apr(?:il)?|may|jun(?:e)?|jul(?:y)?|"
                    + "aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|"
                    + "nov(?:ember)?|dec(?:ember)?)\\b";

    private static final Pattern pattern =
            Pattern.compile(FULL_DATE_REGEX,
                    Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String date, List<? super LocalDate> potentialDates) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(date);
        boolean error = false;

        StringBuffer strippedInput = new StringBuffer();

        while (dateMatcher.find()) {
            int day = dateMatcher.group(1) == null
                      ? 1
                      : Integer.parseInt(dateMatcher.group(1));
            int month = Month.fromAlias(dateMatcher.group(2))
                    .map(Month::getAsNumber)
                    .orElse(currentDate.getMonthValue());
            int year = currentDate.getYear();

            try {
                potentialDates.add(LocalDate.of(year, month, day));
            } catch (DateTimeException ignored) {
                error = true;
            }

            dateMatcher.appendReplacement(strippedInput, "");
        }

        dateMatcher.appendTail(strippedInput);

        return strippedInput.toString();
    }
}
