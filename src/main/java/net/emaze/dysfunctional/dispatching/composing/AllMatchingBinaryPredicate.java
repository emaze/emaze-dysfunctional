package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * A composite binary predicate yielding true when every predicate match
 * (no further predicate is evaluated beyond the first returning false)
 * @param <E1>
 * @param <E2>
 * @author rferranti
 */
public class AllMatchingBinaryPredicate<E1, E2> implements BinaryPredicate<E1, E2> {

    private final Iterable<BinaryPredicate<E1, E2>> predicates;

    public AllMatchingBinaryPredicate(Iterable<BinaryPredicate<E1, E2>> predicates) {
        dbc.precondition(predicates != null, "cannot create AllMatchingBinaryPredicate with a null iterable");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 former, E2 latter) {
        for (BinaryPredicate<E1, E2> predicate : predicates) {
            if (!predicate.accept(former, latter)) {
                return false;
            }
        }
        return true;
    }
}
