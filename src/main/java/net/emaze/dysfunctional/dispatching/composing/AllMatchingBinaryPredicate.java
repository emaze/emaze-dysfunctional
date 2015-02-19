package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;

/**
 * A composite binary predicate yielding true when every predicate match (no
 * further predicate is evaluated beyond the first returning false)
 *
 * @param <E1> the predicates first parameter type
 * @param <E2> the predicates second parameter type
 * @author rferranti
 */
public class AllMatchingBinaryPredicate<E1, E2> implements BiPredicate<E1, E2> {

    private final Iterable<BiPredicate<E1, E2>> predicates;

    public AllMatchingBinaryPredicate(Iterable<BiPredicate<E1, E2>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate and(...) of a null iterable of binary predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean test(E1 former, E2 latter) {
        for (BiPredicate<E1, E2> predicate : predicates) {
            if (!predicate.test(former, latter)) {
                return false;
            }
        }
        return true;
    }
}
