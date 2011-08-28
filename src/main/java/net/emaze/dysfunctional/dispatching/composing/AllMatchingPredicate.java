package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * A composite unary predicate yielding true when every predicate match
 * (no further predicate is evaluated beyond the first returning false)
 * @param <E> the element Type
 * @author rferranti
 */
public class AllMatchingPredicate<E> implements Predicate<E> {

    private final Iterable<Predicate<E>> predicates;

    public AllMatchingPredicate(Iterable<Predicate<E>> predicates) {
        dbc.precondition(predicates != null, "cannot create an AllMatchingPredicate with a null iterable");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E element) {
        for (Predicate<E> predicate : predicates) {
            if (!predicate.accept(element)) {
                return false;
            }
        }
        return true;
    }
}
