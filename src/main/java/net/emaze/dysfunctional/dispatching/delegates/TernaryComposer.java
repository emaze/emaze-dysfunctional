package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes an unary delegate with a binary delegate.
 *
 * @param <T1> the first ternary parameter type
 * @param <T2> the second ternary parameter type
 * @param <T3> the third ternary parameter type
 * @param <T4> unary parameter type and ternary return type
 * @param <R> unary return type
 */
public class TernaryComposer<T1, T2, T3, T4, R> implements TriFunction<T1, T2, T3, R> {

    private final Function<T4, R> unary;
    private final TriFunction<T1, T2, T3, T4> ternary;

    public TernaryComposer(Function<T4, R> unary, TriFunction<T1, T2, T3, T4> ternary) {
        dbc.precondition(unary != null, "cannot compose a null unary delegate");
        dbc.precondition(ternary != null, "cannot compose a null ternary delegate");
        this.unary = unary;
        this.ternary = ternary;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        return unary.apply(ternary.apply(first, second, third));
    }
}
