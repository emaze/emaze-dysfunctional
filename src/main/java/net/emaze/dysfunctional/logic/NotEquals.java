package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class NotEquals<T> implements Predicate<T> {

    private final T lhs;

    public NotEquals(T lhs) {
        dbc.precondition(lhs != null, "lhs of NotEquals cannot be null");
        this.lhs = lhs;
    }

    @Override
    public boolean test(T rhs) {
        return !lhs.equals(rhs);
    }
}
