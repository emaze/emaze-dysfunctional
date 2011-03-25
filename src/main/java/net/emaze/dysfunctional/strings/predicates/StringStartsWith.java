package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

public class StringStartsWith implements Predicate<String> {

    private final String needle;

    public StringStartsWith(String needle) {
        dbc.precondition(needle != null, "cannot create StringStartsWith with a null needle");
        this.needle = needle;
    }

    @Override
    public boolean accept(String haystack) {
        dbc.precondition(haystack != null, "cannot check 'starts with' with a null haystack");
        return haystack.startsWith(needle);
    }
}
