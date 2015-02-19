package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Adapts a binary predicate to a predicate handling pairs.
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class BinaryToUnaryPredicate<T1, T2> implements Predicate<Pair<T1, T2>> {

    private final BiPredicate<T1, T2> predicate;

    public BinaryToUnaryPredicate(BiPredicate<T1, T2> predicate) {
        dbc.precondition(predicate != null, "cannot create a BinaryToUnaryPredicate with a null BinaryPredicate");
        this.predicate = predicate;
    }

    @Override
    public boolean test(Pair<T1, T2> pair) {
        return predicate.test(pair.first(), pair.second());
    }
}
