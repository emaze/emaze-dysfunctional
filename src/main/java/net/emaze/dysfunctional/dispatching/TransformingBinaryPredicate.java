package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Composes a predicate with a binary function (predicate ° function).
 *
 * @param <R> the function result type
 * @param <T1> the function first parameter type
 * @param <T2> the function second parameter type
 * @author rferranti
 */
public class TransformingBinaryPredicate<R, T1, T2> implements BiPredicate<T1, T2> {

    private final Predicate<R> predicate;
    private final BiFunction<T1, T2, R> function;

    public TransformingBinaryPredicate(Predicate<R> predicate, BiFunction<T1, T2, R> function) {
        dbc.precondition(predicate != null, "cannot compose function with a null predicate");
        dbc.precondition(function != null, "cannot compose predicate with a null function");
        this.predicate = predicate;
        this.function = function;
    }

    @Override
    public boolean test(T1 first, T2 second) {
        return predicate.test(function.apply(first, second));
    }
}
