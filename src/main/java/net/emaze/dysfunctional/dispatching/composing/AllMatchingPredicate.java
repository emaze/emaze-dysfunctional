package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

/**
 * A composite unary predicate yielding true when every predicate match (no
 * further predicate is evaluated beyond the first returning false)
 *
 * @param <E> the element Type
 * @author rferranti
 */
public class AllMatchingPredicate<E> implements Predicate<E> {

    private final Iterable<Predicate<E>> predicates;

    public AllMatchingPredicate(Iterable<Predicate<E>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate and(...) of a null iterable of predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean test(E element) {
        for (Predicate<E> predicate : predicates) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }
}
