package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;

/**
 * A composite binary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 *
 * @param <E1> the predicates first parameter type
 * @param <E2> the predicates second parameter type
 * @author rferranti
 */
public class FirstMatchingBinaryPredicate<E1, E2> implements BiPredicate<E1, E2> {

    private final Iterable<BiPredicate<E1, E2>> predicates;

    public FirstMatchingBinaryPredicate(Iterable<BiPredicate<E1, E2>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate or(...) of a null iterable of binary predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean test(E1 first, E2 second) {
        for (BiPredicate<E1, E2> predicate : predicates) {
            if (predicate.test(first, second)) {
                return true;
            }
        }
        return false;
    }
}
