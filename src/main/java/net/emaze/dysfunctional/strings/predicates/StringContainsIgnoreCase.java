package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.logic.Predicate;

public class StringContainsIgnoreCase implements Predicate<String> {

    private final String needle;

    public StringContainsIgnoreCase(String needle) {
        dbc.precondition(needle != null, "cannot create StringContainsIgnoreCase with a null needle");
        this.needle = needle.toLowerCase();
    }

    @Override
    public boolean test(String haystack) {
        dbc.precondition(haystack != null, "cannot check if a needle is contained in a null haystack");
        return haystack.toLowerCase().contains(needle);
    }
}
