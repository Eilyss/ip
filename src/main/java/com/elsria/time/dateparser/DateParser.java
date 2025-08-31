package com.elsria.time.dateparser;

import java.time.LocalDate;
import java.util.List;

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
