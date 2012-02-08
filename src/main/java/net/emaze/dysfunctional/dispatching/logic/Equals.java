package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A predicate evaluating equality between objects.
 *
 * @author rferranti
 */
public class Equals<T> implements Predicate<T> {

    private final T lhs;

    public Equals(T lhs) {
        dbc.precondition(lhs != null, "lhs of Equals cannot be null");
        this.lhs = lhs;
    }

    @Override
    public boolean accept(T rhs) {
        return lhs.equals(rhs);
    }
}
