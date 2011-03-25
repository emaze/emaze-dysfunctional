package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

public class StringEndsWith implements Predicate<String> {

    private String needle;

    public StringEndsWith(String needle) {
        dbc.precondition(needle != null, "cannot create StringEndsWith with a null needle");
        this.needle = needle;
    }

    @Override
    public boolean accept(String haystack) {
        dbc.precondition(haystack != null, "cannot check 'ends with' with a null haystack");
        return haystack.endsWith(needle);
    }
}
