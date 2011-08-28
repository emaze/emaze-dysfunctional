package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * A composite binary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 * @param <E1>
 * @param <E2>
 * @author rferranti
 */
public class FirstMatchingBinaryPredicate<E1, E2> implements BinaryPredicate<E1, E2> {

    private final Iterable<BinaryPredicate<E1, E2>> predicates;

    public FirstMatchingBinaryPredicate(Iterable<BinaryPredicate<E1, E2>> predicates) {
        dbc.precondition(predicates != null, "cannot create a FirstMatchingBinaryPredicate with a null iterable");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 first, E2 second) {
        for (BinaryPredicate<E1, E2> predicate : predicates) {
            if (predicate.accept(first, second)) {
                return true;
            }
        }
        return false;
    }
}
