package com.elsria.time;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum Month {
    JANUARY(1, Set.of("jan", "january")),
    FEBRUARY(2, Set.of("feb", "february")),
    MARCH(3, Set.of("mar", "march")),
    APRIL(4, Set.of("apr", "april")),
    MAY(5, Set.of("may")),
    JUNE(6, Set.of("jun", "june")),
    JULY(7, Set.of("jul", "july")),
    AUGUST(8, Set.of("aug", "august")),
    SEPTEMBER(9, Set.of("sep", "sept", "september")),
    OCTOBER(10, Set.of("oct", "october")),
    NOVEMBER(11, Set.of("nov", "november")),
    DECEMBER(12, Set.of("dec", "december"));

    private static final Map<String, Month> ALIAS_MAP = new HashMap<>();

    static {
        for (Month month : Month.values()) {
            for (String alias : month.aliases) {
                ALIAS_MAP.put(alias, month);
            }
        }
    }

    private int asNumber;
    private Set<String> aliases;


    Month(int number, Set<String> aliases) {
        this.asNumber = number;
        this.aliases = aliases;
    }

    public int getAsNumber() {
        return this.asNumber;
    }

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }

    public static Optional<Month> fromAlias(String alias) {
        if (alias == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(ALIAS_MAP.get(alias.toLowerCase()));
    }
}
