package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite ternary predicate yielding true when every predicate match
 * (no further predicate is evaluated beyond the first returning false)
 * @param <E1>
 * @param <E2>
 * @param <E3>
 * @author rferranti
 */
public class AllMatchingTernaryPredicate<E1, E2, E3> implements TernaryPredicate<E1, E2, E3> {

    private final Iterable<TernaryPredicate<E1, E2, E3>> predicates;

    public AllMatchingTernaryPredicate(Iterable<TernaryPredicate<E1, E2, E3>> predicates) {
        dbc.precondition(predicates != null, "cannot create an AllMatchingTernaryPredicate with a null iterable");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        for (TernaryPredicate<E1, E2, E3> predicate : predicates) {
            if (!predicate.accept(first, second, third)) {
                return false;
            }
        }
        return true;
    }
}
