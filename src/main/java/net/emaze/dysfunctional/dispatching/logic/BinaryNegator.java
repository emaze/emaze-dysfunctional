package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Negates a predicate.
 *
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
     * Tests the nested predicate and negates it.
     *
     * @param former the former parameter
     * @param latter the latter parameter
     * @return true if the inner predicate returns false, false otherwise
     */
    @Override
    public boolean accept(T1 former, T2 latter) {
        return !predicate.accept(former, latter);
    }
}
