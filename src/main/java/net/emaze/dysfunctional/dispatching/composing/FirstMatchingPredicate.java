package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * A composite unary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 *
 * @param <E> the element Type
 * @author rferranti
 */
public class FirstMatchingPredicate<E> implements Predicate<E> {

    private final Iterable<Predicate<E>> predicates;

    public FirstMatchingPredicate(Iterable<Predicate<E>> predicates) {
        dbc.precondition(predicates != null, "cannot avaluate or(...) of a null iterable of predicates");
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
