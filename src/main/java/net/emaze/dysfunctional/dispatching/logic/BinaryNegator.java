package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * negates a predicate
 *  x => !predicate(x)
 * @param <T1> the former element type parameter
 * @param <T2> the latter element type parameter
 * @author rferranti
 */
public class BinaryNegator<T1, T2> implements BinaryPredicate<T1, T2> {

    private final BinaryPredicate<T1, T2> predicate;

    public BinaryNegator(BinaryPredicate<T1, T2> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null binary predicate");
        this.predicate = predicate;
    }

    /**
     * tests the nested predicate and negates it
     * @param former
     * @param latter
     * @return true if the inner predicate returns false, false otherwhise
     */
    @Override
    public boolean test(T1 former, T2 latter) {
        return !predicate.test(former, latter);
    }
}
