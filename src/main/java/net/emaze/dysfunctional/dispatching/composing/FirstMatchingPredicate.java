package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
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

    private final Iterator<Predicate<E>> predicates;

    public FirstMatchingPredicate(Iterator<Predicate<E>> predicates) {
        dbc.precondition(predicates != null, "cannot create a FirstMatchingPredicate with a null iterator");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E element) {
        while (predicates.hasNext()) {
            if (predicates.next().accept(element)) {
                return true;
            }
        }
        return false;
    }
}
