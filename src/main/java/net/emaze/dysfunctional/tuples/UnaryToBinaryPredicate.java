package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.logic.BinaryPredicate;
import net.emaze.dysfunctional.logic.Predicate;

/**
 * Adapts a unary predicate handling pairs to a binary predicate.
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class UnaryToBinaryPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    private final Predicate<Pair<T1, T2>> predicate;

    public UnaryToBinaryPredicate(Predicate<Pair<T1, T2>> predicate) {
        dbc.precondition(predicate != null, "cannot create a UnaryToBinaryPredicate with a null Predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean test(T1 first, T2 second) {
        return predicate.test(Pair.of(first, second));
    }
}
