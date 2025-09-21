package com.neokortex.time.dateparser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FullDateParserTest {
    private static final FullDateParser fdp = new FullDateParser();

    @Test
    public void testWithAffixAndYear() {
        String testString = """
                25th December 2025,
                3rd September 2025,
                15th January 1979,
                2nd March 2013,
                1st May 2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffixAndYear() {
        String testString = """
                25 December 2025,
                3 September 2025,
                15 January 1979,
                2 March 2013,
                1 May 2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithAffixNoYear() {
        String testString = """
                25th December,
                3rd September,
                15th January,
                2nd March,
                1st May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffixNoYear() {
        String testString = """
                25 December,
                3 September,
                15 January,
                2 March,
                1 May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testMixDates() {
        String testString = """
                25th December,
                3 September,
                15 January,
                2 March,
                1 May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testMixedFormat() {
        String testString = """
                25th December 2025,
                3 September 2025,
                15th January,
                2 March,
                1st May 2024
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testGarbledDates() {
        String testString = """
                25th December 2025 with a bunch of spaces to go
                Something before the date shouldn't change anything 3 September 2024,
                Before... 15th January 2023 and after...,
                1st M and 2nd 1st May 2024 2025 2026 garbage data
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2024, 9, 3),
                LocalDate.of(2023, 1, 15),
                LocalDate.of(2024, 5, 1)
        );

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(testDateSolutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testIncorrectDates() {
        String testString = """
                Cannot merge front25th December 2025
                3 SeptemberCannot merge back
                15th Nothing In Between January The Dates 2023,
                So Anyway 2 this should March not be 2025 detected,
                May 1st 2024, Wrong Format!
                """;

        List<LocalDate> result = fdp.parse(testString);
        for (LocalDate localDate : result) {
            System.out.println(localDate);
        }


        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(0, result.size());
    }
}
