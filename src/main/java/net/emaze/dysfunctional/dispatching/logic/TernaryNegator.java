package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * negates a predicate
 *  x => !predicate(x)
 * @param <T1> the first element type parameter
 * @param <T2> the second element type parameter
 * @param <T3> the third element type parameter
 * @author rferranti
 */
public class TernaryNegator<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final TernaryPredicate<T1, T2, T3> predicate;

    public TernaryNegator(TernaryPredicate<T1, T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null Ternary predicate");
        this.predicate = predicate;
    }

    /**
     * tests the nested predicate and negates it
     * @param first
     * @param second
     * @param third 
     * @return true if the inner predicate returns false, false otherwhise
     */
    @Override
    public boolean test(T1 first, T2 second, T3 third) {
        return !predicate.test(first, second, third);
    }
}
