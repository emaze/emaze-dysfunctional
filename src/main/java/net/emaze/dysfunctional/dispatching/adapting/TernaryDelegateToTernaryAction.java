package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a ternary delegate to a ternary action. Adapting is performed by
 * ignoring the adapted delegate result.
 *
 * @author rferranti
 * @param <R> the adapted delegate result type
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 */
public class TernaryDelegateToTernaryAction<T1, T2, T3, R> implements TernaryAction<T1, T2, T3> {

    private final TriFunction<T1, T2, T3, R> adapted;

    public TernaryDelegateToTernaryAction(TriFunction<T1, T2, T3, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary delegate to ternary action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        adapted.apply(first, second, third);
    }
}
