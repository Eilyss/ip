package com.elsria.time.dateparser;

import com.elsria.time.TimeConstants;

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
    private static final Pattern pattern = Pattern.compile(DAY_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public List<LocalDate> parse(String date) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(date);
        ArrayList<LocalDate> potentialDates = new ArrayList<>();

        while (dateMatcher.find()) {
            DayOfWeek found = DayOfWeek.valueOf(dateMatcher.group(2).toUpperCase());
            int dayDifference = getDayDifference(currentDate, found, dateMatcher);
            potentialDates.add(LocalDate.now().plusDays(dayDifference));
        }

        return potentialDates;
    }

    private static int getDayDifference(LocalDate currentDate, DayOfWeek found, Matcher dateMatcher) {
        DayOfWeek today = currentDate.getDayOfWeek();
        int dayDifference = (found.getValue() - today.getValue() + 7) % 7;
        if (dateMatcher.group(1).equals("next") && found.getValue() - today.getValue() >= 0) {
            dayDifference += 7;
        } else if (dateMatcher.group(2).equals("following")) {
            dayDifference += 7;
            if (found.getValue() - today.getValue() >= 0) {
                dayDifference += 7;
            }
        }
        return dayDifference;
    }
}
