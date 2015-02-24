package net.emaze.dysfunctional.tuples;

import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Adapts a ternary predicate handling triples to a predicate handling triples.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class TernaryToUnaryPredicate<T1, T2, T3> implements Predicate<Triple<T1, T2, T3>> {

    private final TriPredicate<T1, T2, T3> predicate;

    public TernaryToUnaryPredicate(TriPredicate<T1, T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot create a TernaryToUnaryPredicate with a null TernaryPredicate");
        this.predicate = predicate;
    }

    @Override
    public boolean test(Triple<T1, T2, T3> triple) {
        return predicate.test(triple.first(), triple.second(), triple.third());
    }
}
