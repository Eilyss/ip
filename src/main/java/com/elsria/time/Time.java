package com.elsria.time;

import com.elsria.exceptions.InvalidDateSerializationException;
import com.elsria.exceptions.InvalidTimeSerializationException;
import com.elsria.time.dateparser.CompositeDateParser;
import com.elsria.time.dateparser.DateParser;
import com.elsria.time.timeparser.CompositeTimeParser;
import com.elsria.time.timeparser.TimeParser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Time {
    private enum DisplayType {
        FULL,
        TIME_ONLY,
        DATE_ONLY
    }

    private final LocalDate date;
    private final LocalTime time;
    private DisplayType displayType;

    public Time(LocalDateTime time) {
        this.date = time.toLocalDate();
        this.time = time.toLocalTime();
        this.displayType = DisplayType.FULL;
    }

    public Time(LocalDate date) {
        this.date = date;
        this.time = LocalTime.of(23, 59, 0);
        this.displayType = DisplayType.DATE_ONLY;
    }

    public Time(LocalTime time) {
        this.date = LocalDate.now();
        this.time = time;
        this.displayType = DisplayType.TIME_ONLY;
    }

    private Time(LocalDate date, LocalTime time, DisplayType displayType) {
        this.date = date;
        this.time = time;
        this.displayType = DisplayType.FULL;
    }

    public void setDisplayTypeToFull() {
        this.displayType = DisplayType.FULL;
    }

    public void setDisplayTypeToTimeOnly() {
        this.displayType = DisplayType.TIME_ONLY;
    }

    public void setDisplayTypeToDateOnly() {
        this.displayType = DisplayType.DATE_ONLY;
    }

    public static Time parseTime(String input) {
        CompositeDateParser dateParser = DateParser.createDefaultParser();
        List<LocalDate> possibleDates = dateParser.processString(input);
        CompositeTimeParser timeParser = TimeParser.createDefaultParser();
        List<LocalTime> possibleTimes = timeParser.processString(input);

        boolean hasDate = false;
        boolean hasTime = false;
        LocalDate dateFound = LocalDate.now();
        LocalTime timeFound = LocalTime.of(23, 59);
        if (!possibleDates.isEmpty()) {
            dateFound = possibleDates.getLast();
            hasDate = true;
        }

        if (!possibleTimes.isEmpty()) {
            timeFound = possibleTimes.getLast();
            hasTime = true;
        }

        if (!hasDate && !hasTime) {
            return null;
        }

        return new Time(dateFound, timeFound, getDisplayType(hasDate, hasTime));
    }

    public String serialize() {
        return String.format("%d,%d:%d:%d,%d/%d/%d", this.displayType.ordinal(),
                this.time.getHour(), this.time.getMinute(), this.time.getSecond(),
                this.date.getDayOfMonth(), this.date.getMonthValue(), this.date.getYear());
    }

    public static Time deserialize(String input) {
        String[] parts = input.split(",");

        DisplayType displayType = intToDisplayType(Integer.parseInt(parts[0]));
        LocalTime time = deserializeTime(parts[1]);
        LocalDate date = deserializeDate(parts[2]);

        return new Time(date, time, getDisplayType(false, false));
    }

    private static DisplayType getDisplayType(boolean hasDate, boolean hasTime) {
        if (hasDate && hasTime) {
            return DisplayType.FULL;
        }
        if (hasDate) {
            return DisplayType.DATE_ONLY;
        }
        if (hasTime) {
            return DisplayType.TIME_ONLY;
        }
        throw new IllegalArgumentException("Cannot simultaneously have no date and time!");

    }

    private static DisplayType intToDisplayType(int ordinal) {
        if (ordinal < 0 || ordinal >= DisplayType.values().length) {
            throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }

        return DisplayType.values()[ordinal];
    }

    private static LocalTime deserializeTime(String input) {
        String[] parts = input.split(":");
        if (parts.length != 3) {
            throw new InvalidTimeSerializationException("Incorrect time format!");
        }
        int hour, minute, second;
        try {
            hour = Integer.parseInt(parts[0]);
            minute = Integer.parseInt(parts[1]);
            second = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidTimeSerializationException("Time string contains non-integers!");
        }

        LocalTime time;
        try {
            time = LocalTime.of(hour, minute, second);
        } catch (DateTimeException e) {
            throw new InvalidTimeSerializationException("Time string given is not a valid time!");
        }

        return time;
    }

    private static LocalDate deserializeDate(String input) {
        String[] parts = input.split("/");
        if (parts.length != 3) {
            throw new InvalidDateSerializationException("Incorrect date format!");
        }

        int day, month, year;
        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidDateSerializationException("Time string contains non-integers!");
        }

        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidDateSerializationException("Time string given is not a valid time!");
        }

        return date;
    }

    @Override
    public String toString() {
        String dateString = String.format("%d %s %d", date.getDayOfMonth(), date.getMonth(), date.getYear());
        String timeString = String.format("%2d%2d", time.getHour(), time.getMinute());

        switch (displayType) {
        case FULL:
            return timeString + " " + dateString;
        case TIME_ONLY:
            return timeString;
        case DATE_ONLY:
            return dateString;
        default:
            return "Not a valid time";
        }
    }
}
