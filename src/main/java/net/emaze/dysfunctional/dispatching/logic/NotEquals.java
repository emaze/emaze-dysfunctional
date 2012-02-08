package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A unary predicate evaluating if objects are not equals.
 *
 * Same as: {@code Logic.not(new Equals<E>()); }
 *
 * @author rferranti
 */
public class NotEquals<T> implements Predicate<T> {

    private final T lhs;

    public NotEquals(T lhs) {
        dbc.precondition(lhs != null, "lhs of NotEquals cannot be null");
        this.lhs = lhs;
    }

    /**
     * Yields true if lhs and rhs are not equals.
     *
     * @param rhs the right hand side of the equality
     * @return false if objects are equals. true otherwise
     */
    @Override
    public boolean accept(T rhs) {
        return !lhs.equals(rhs);
    }
}
