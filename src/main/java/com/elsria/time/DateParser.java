package com.elsria.time;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DateParser {

    public final static Set<Function<String, List<LocalDate>>> parseFunctions =
            new HashSet<>(Set.of(Day::parse, FullDate::parse, SimpleDate::parse, Phrases::parse));

    public static class Day {
        private static final Pattern pattern = TimeConstants.DAY_PATTERN;

        private Day() {

        }

        public static List<LocalDate> parse(String date) {
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


    public static class FullDate {
        private static final Pattern pattern = TimeConstants.FULL_DATE_PATTERN;

        private FullDate() {}
        public static List<LocalDate> parse(String date) {
            LocalDate currentDate = LocalDate.now();
            Matcher dateMatcher = pattern.matcher(date);
            ArrayList<LocalDate> potentialDates = new ArrayList<>();

            while (dateMatcher.find()) {
                int day = dateMatcher.group(1) == null ? 1 : Integer.parseInt(dateMatcher.group(1));
                int month = Month.fromAlias(dateMatcher.group(2)).map(Month::getAsNumber).orElse(currentDate.getMonthValue());
                int year = dateMatcher.group(3) == null ? currentDate.getYear() : Integer.parseInt(dateMatcher.group(3));
                year += (year < 100) ? 2000 : 0;

                try {
                    potentialDates.add(LocalDate.of(year, month, day));
                } catch (Exception ignored) {
                }
            }
            return potentialDates;
        }
    }

    public static class SimpleDate  {
        private static final Pattern pattern = TimeConstants.SIMPLE_DATE_PATTERN;

        private SimpleDate() {}

        public static List<LocalDate> parse(String date) {
            LocalDate currentDate = LocalDate.now();
            Matcher dateMatcher = pattern.matcher(date);
            ArrayList<LocalDate> potentialDates = new ArrayList<>();

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

                }

            }
            return potentialDates;
        }
    }

    public static class Phrases {
        public static List<LocalDate> parse(String date) {
            LocalDate currentDate = LocalDate.now();
            ArrayList<LocalDate> potentialDates = new ArrayList<>();
            date = date.toLowerCase();

            if (date.contains("today")) {
                potentialDates.add(LocalDate.now());
            }
            if (date.contains("tomorrow")) {
                potentialDates.add(LocalDate.now().plusDays(1));
            }
            if (date.contains("following day")) {
                potentialDates.add(LocalDate.now().plusDays(2));
            }
            if (date.contains("next week")) {
                potentialDates.add(LocalDate.now().plusDays(7));
            }
            if (date.contains("next month")) {
                potentialDates.add(LocalDate.now().plusMonths(1));
            }
            if (date.contains("next year")) {
                potentialDates.add(LocalDate.now().plusYears(1));
            }

            return potentialDates;

        }
    }
}
