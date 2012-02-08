package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * A composite ternary predicate yielding true when every predicate match (no
 * further predicate is evaluated beyond the first returning false)
 *
 * @param <E1> the predicates first parameter type
 * @param <E2> the predicates second parameter type
 * @param <E3> the predicates third parameter type
 * @author rferranti
 */
public class AllMatchingTernaryPredicate<E1, E2, E3> implements TernaryPredicate<E1, E2, E3> {

    private final Iterator<TernaryPredicate<E1, E2, E3>> predicates;

    public AllMatchingTernaryPredicate(Iterator<TernaryPredicate<E1, E2, E3>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate every of a null iterator of ternary predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        while (predicates.hasNext()) {
            if (!predicates.next().accept(first, second, third)) {
                return false;
            }
        }
        return true;
    }
}
