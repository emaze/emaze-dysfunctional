package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * A composite ternary predicate yielding true when the first predicate matches
 * (no further predicate is evaluated beyond the first returning true)
 *
 * @param <E1> the predicates first parameter type
 * @param <E2> the predicates second parameter type
 * @param <E3> the predicates third parameter type
 * @author rferranti
 */
public class FirstMatchingTernaryPredicate<E1, E2, E3> implements TernaryPredicate<E1, E2, E3> {

    private final Iterable<TernaryPredicate<E1, E2, E3>> predicates;

    public FirstMatchingTernaryPredicate(Iterable<TernaryPredicate<E1, E2, E3>> predicates) {
        dbc.precondition(predicates != null, "cannot evaluate or(...) of a null iterable of ternary predicates");
        this.predicates = predicates;
    }

    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        for (TernaryPredicate<E1, E2, E3> predicate : predicates) {
            if (predicate.accept(first, second, third)) {
                return true;
            }
        }
        return false;
    }
}
