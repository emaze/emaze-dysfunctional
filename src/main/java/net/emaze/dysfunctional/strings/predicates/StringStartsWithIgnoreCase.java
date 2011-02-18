package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.logic.Predicate;

public class StringStartsWithIgnoreCase implements Predicate<String> {

    private final String needle;

    public StringStartsWithIgnoreCase(String needle) {
        dbc.precondition(needle != null, "cannot create StringStartsWithIgnoreCase with a null needle");
        this.needle = needle.toLowerCase();
    }

    @Override
    public boolean test(String haystack) {
        dbc.precondition(haystack != null, "cannot check 'starts with' with a null haystack");
        return haystack.toLowerCase().startsWith(needle);
    }
}
