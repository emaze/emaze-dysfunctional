package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Adapts a unary predicate handling triples to a ternary predicate.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class UnaryToTernaryPredicate<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final Predicate<Triple<T1, T2, T3>> predicate;

    public UnaryToTernaryPredicate(Predicate<Triple<T1, T2, T3>> predicate) {
        dbc.precondition(predicate != null, "cannot create a UnaryToTernaryPredicate with a null Predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        return predicate.test(Triple.of(first, second, third));
    }
}
