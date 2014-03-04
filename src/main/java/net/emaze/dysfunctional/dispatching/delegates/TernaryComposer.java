package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes an unary delegate with a binary delegate.
 *
 * @param <R> unary return type
 * @param <T1> unary parameter type and ternary return type
 * @param <T2> the first ternary parameter type
 * @param <T3> the second ternary parameter type
 * @param <T4> the third ternary parameter type
 * @param unary the unary delegate to be composed
 * @param ternary the ternary delegate to be composed
 */
public class TernaryComposer<R, T1, T2, T3, T4> implements TernaryDelegate<R, T2, T3, T4> {

    private final Delegate<R, T1> unary;
    private final TernaryDelegate<T1, T2, T3, T4> ternary;

    public TernaryComposer(Delegate<R, T1> unary, TernaryDelegate<T1, T2, T3, T4> ternary) {
        dbc.precondition(unary != null, "cannot compose a null unary delegate");
        dbc.precondition(ternary != null, "cannot compose a null ternary delegate");
        this.unary = unary;
        this.ternary = ternary;
    }

    @Override
    public R perform(T2 first, T3 second, T4 third) {
        return unary.perform(ternary.perform(first, second, third));
    }
}
