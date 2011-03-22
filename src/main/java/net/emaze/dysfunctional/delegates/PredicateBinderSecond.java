package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.logic.BinaryPredicate;
import net.emaze.dysfunctional.logic.Predicate;

/**
 * Binary to unary predicate adapter (rcurry)
 * @param <T> the former element Type
 * @param <U> the latter element Type
 * @author rferranti
 */
public class PredicateBinderSecond<T, U> implements Predicate<T> {

    private final BinaryPredicate<T, U> predicate;
    private final U second;

    public PredicateBinderSecond(BinaryPredicate<T, U> predicate, U second) {
        dbc.precondition(predicate != null, "cannot bind the second parameter of a null binary predicate");
        this.predicate = predicate;
        this.second = second;
    }

    @Override
    public boolean test(T first) {
        return predicate.test(first, second);
    }
}
