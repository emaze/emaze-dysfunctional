package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * A composite unary predicate yielding true when every predicate match (no
 * further predicate is evaluated beyond the first returning false)
 *
 * @param <E> the element Type
 * @author rferranti
 */
public class AllMatchingPredicate<E> implements Predicate<E> {

    private final Iterator<Predicate<E>> predicates;

    public AllMatchingPredicate(Iterator<Predicate<E>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate every of a null iterator of predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E element) {
        while (predicates.hasNext()) {
            if (!predicates.next().accept(element)) {
                return false;
            }
        }
        return true;
    }
}
