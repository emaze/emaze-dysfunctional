package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.logic.Predicate;

public class StringEndsWithIgnoreCase implements Predicate<String> {

    private final String needle;

    public StringEndsWithIgnoreCase(String needle) {
        dbc.precondition(needle != null, "cannot create StringEndsWithIgnoreCase with a null needle");
        this.needle = needle.toLowerCase();
    }

    @Override
    public boolean test(String haystack) {
        dbc.precondition(haystack != null, "cannot check 'ends with' with a null haystack");
        return haystack.toLowerCase().endsWith(needle);
    }
}
