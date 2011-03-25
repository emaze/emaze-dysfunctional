package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

public class StringContains implements Predicate<String> {

    private final String needle;

    public StringContains(String needle) {
        dbc.precondition(needle != null, "cannot create StringContains with a null needle");
        this.needle = needle;
    }

    @Override
    public boolean test(String haystack) {
        dbc.precondition(haystack != null, "cannot check if a needle is contained in a null haystack");
        return haystack.contains(needle);
    }
}
