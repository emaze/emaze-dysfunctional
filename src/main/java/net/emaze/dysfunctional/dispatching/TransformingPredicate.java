package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Composes a predicate with a function (predicate ° function).
 *
 * @param <R> the function result type
 * @param <T> the function parameter type
 * @author rferranti
 */
public class TransformingPredicate<R, T> implements Predicate<T> {

    private final Predicate<R> predicate;
    private final Function<T, R> function;

    public TransformingPredicate(Predicate<R> predicate, Function<T, R> function) {
        dbc.precondition(predicate != null, "cannot compose function with a null predicate");
        dbc.precondition(function != null, "cannot compose predicate with a null function");
        this.predicate = predicate;
        this.function = function;
    }

    @Override
    public boolean test(T element) {
        return predicate.test(function.apply(element));
    }
}
