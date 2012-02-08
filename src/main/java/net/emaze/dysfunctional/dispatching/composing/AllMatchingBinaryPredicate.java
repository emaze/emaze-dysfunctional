package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * A composite binary predicate yielding true when every predicate match (no
 * further predicate is evaluated beyond the first returning false)
 *
 * @param <E1> the predicates first parameter type
 * @param <E2> the predicates second parameter type
 * @author rferranti
 */
public class AllMatchingBinaryPredicate<E1, E2> implements BinaryPredicate<E1, E2> {

    private final Iterator<BinaryPredicate<E1, E2>> predicates;

    public AllMatchingBinaryPredicate(Iterator<BinaryPredicate<E1, E2>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate every of a null iterator of binary predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 former, E2 latter) {
        while (predicates.hasNext()) {
            if (!predicates.next().accept(former, latter)) {
                return false;
            }
        }
        return true;
    }
}
