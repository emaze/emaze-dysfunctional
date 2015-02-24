package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Composes a predicate with a ternary function (predicate ° function).
 *
 * @param <R> the function result type
 * @param <T1> the function first parameter type
 * @param <T2> the function second parameter type
 * @param <T3> the function third parameter type
 * @author rferranti
 */
public class TransformingTernaryPredicate<T1, T2, T3, R> implements TriPredicate<T1, T2, T3> {

    private final Predicate<R> predicate;
    private final TriFunction<T1, T2, T3, R> function;

    public TransformingTernaryPredicate(Predicate<R> predicate, TriFunction<T1, T2, T3, R> function) {
        dbc.precondition(predicate != null, "cannot compose function with a null predicate");
        dbc.precondition(function != null, "cannot compose predicate with a null function");
        this.predicate = predicate;
        this.function = function;
    }

    @Override
    public boolean test(T1 first, T2 second, T3 third) {
        return predicate.test(function.apply(first, second, third));
    }
}
