package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A composite unary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 * @param <E> the element Type
 * @author rferranti
 */
public class FirstMatchingPredicate<E> implements Predicate<E> {

    private final Iterable<Predicate<E>> predicates;

    public FirstMatchingPredicate(Iterable<Predicate<E>> predicates) {
        dbc.precondition(predicates != null, "cannot create a FirstMatchingPredicate with a null iterable");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E element) {
        for (Predicate<E> predicate : predicates) {
            if (predicate.accept(element)) {
                return true;
            }
        }
        return false;
    }
}
