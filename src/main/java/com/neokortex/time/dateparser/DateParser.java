package com.neokortex.time.dateparser;

import java.time.LocalDate;
import java.util.List;

public abstract class DateParser {

    public static CompositeDateParser createDefaultParser() {
        return new CompositeDateParser(List.of(
                new FullDateParser(),
                new FullDateNoYearParser(),
                new FullDateMonthFirstParser(),
                new FullDateMonthFirstNoYearParser(),
                new SimpleDateParser(),
                new SimpleDateNoYearParser(),
                new SimpleDateMonthFirstParser(),
                new SimpleDateMonthFirstNoYearParser(),
                new DayOfWeekParser(),
                new PhraseParser()
        ));
    }

    public abstract String parse(String date, List<? super LocalDate> potentialDates);
}
