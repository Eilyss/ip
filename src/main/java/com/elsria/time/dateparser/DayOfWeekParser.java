package com.elsria.time.dateparser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayOfWeekParser extends DateParser {
    private static final String DAY_REGEX =
            "(?i)"
                    + "(?:\\b(next|following)\\s+)?"
                    + "\\b(sun(?:day)?|mon(?:day)?|"
                    + "tue(?:s|sday)?|wed(?:s|nesday)?|"
                    + "thu(?:r|rs|rsday)?|fri(?:day)?|sat(?:urday)?)\\b";
    private static final Pattern pattern =
            Pattern.compile(DAY_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String date, List<? super LocalDate> potentialDates) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(date);

        StringBuffer strippedInput = new StringBuffer();

        while (dateMatcher.find()) {
            DayOfWeek found = DayOfWeek.valueOf(dateMatcher.group(2).toUpperCase());
            int dayDifference = getDayDifference(currentDate, found, dateMatcher);
            potentialDates.add(LocalDate.now().plusDays(dayDifference));
            dateMatcher.appendReplacement(strippedInput, "");
        }

        dateMatcher.appendTail(strippedInput);

        return strippedInput.toString();
    }

    private static int getDayDifference(
            LocalDate currentDate, DayOfWeek found, Matcher dateMatcher) {
        DayOfWeek today = currentDate.getDayOfWeek();
        int dayDifference = (found.getValue() - today.getValue() + 7) % 7;

        if (dateMatcher.group(1) != null) {
            String prefix = dateMatcher.group(1);
            if (prefix.equals("next")) {
                dayDifference += 7;
            } else if (prefix.equals("following")) {
                dayDifference += 14;
            }
        }

        return dayDifference;
    }
}
