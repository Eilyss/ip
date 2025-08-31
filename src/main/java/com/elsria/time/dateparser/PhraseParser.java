package com.elsria.time.dateparser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhraseParser extends DateParser {
    private static final String PHRASE_REGEX =
            "(?i)"
                    + "\\b(today|tomorrow|"
                    + "day\\s+after|following\\s+day|"
                    + "next\\s+(?:week|month|year))\\b";

    private static final Pattern pattern = Pattern.compile(PHRASE_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public List<LocalDate> parse(String input) {
        LocalDate currentDate = LocalDate.now();
        Matcher matcher = pattern.matcher(input);
        ArrayList<LocalDate> potentialDates = new ArrayList<>();

        while (matcher.find()) {
            String keyword = matcher.group(1)
                    .replaceAll("\\s+", " ")
                    .trim()
                    .toLowerCase();

            switch (keyword) {
            case "today":
                potentialDates.add(currentDate);
                break;
            case "tomorrow":
                potentialDates.add(currentDate.plusDays(1));
                break;
            case "day after":
            case "following day":
                potentialDates.add(currentDate.plusDays(2));
                break;
            case "next week":
                potentialDates.add(currentDate.plusWeeks(1));
                break;
            case "next month":
                potentialDates.add(currentDate.plusMonths(1));
                break;
            case "next year":
                potentialDates.add(currentDate.plusYears(1));
                break;
            default:
                break;
            }
        }

        return potentialDates;

    }
}
