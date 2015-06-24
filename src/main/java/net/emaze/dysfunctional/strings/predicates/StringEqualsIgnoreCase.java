package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

public class StringEqualsIgnoreCase implements Predicate<String> {

    private final String lhs;

    public StringEqualsIgnoreCase(String lhs) {
        dbc.precondition(lhs != null, "cannot create StringEqualsIgnoreCase with a null lhs");
        this.lhs = lhs;
    }

    @Override
    public boolean test(String rhs) {
        dbc.precondition(rhs != null, "cannot check equalsIgnoreCase vs a null rhs");
        return lhs.equalsIgnoreCase(rhs);
    }
}
