package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

public class StringEqualsIgnoreCase implements Predicate<String> {

    private final String lhs;

    public StringEqualsIgnoreCase(String lhs) {
        dbc.precondition(lhs != null, "cannot create StringEqualsIgnoreCase with a null lhs");
        this.lhs = lhs;
    }

    @Override
    public boolean accept(String rhs) {
        dbc.precondition(rhs != null, "cannot check equalsIgnoreCase vs a null rhs");
        return lhs.equalsIgnoreCase(rhs);
    }
}
