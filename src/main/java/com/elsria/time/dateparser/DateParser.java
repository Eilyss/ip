package com.elsria.time.dateparser;

import com.elsria.time.Month;
import com.elsria.time.TimeConstants;

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

    public static CompositeDateParser createDefaultParser() {
        return new CompositeDateParser(List.of(
                new DayOfWeekParser(),
                new SimpleDateParser(),
                new FullDateParser(),
                new FullDateMonthFirstParser(),
                new PhraseParser()
        ));
    }

    public abstract List<LocalDate> parse(String date);
}
