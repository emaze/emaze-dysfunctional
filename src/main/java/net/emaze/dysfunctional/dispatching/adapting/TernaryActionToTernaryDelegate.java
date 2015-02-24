package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a ternary action to a ternary delegate. Adapter result type is Void
 * and always yields null.
 *
 * @param <T1> the adapted action first parameter type
 * @param <T2> the adapted action second parameter type
 * @param <T3> the adapted action third parameter type
 * @author rferranti
 */
public class TernaryActionToTernaryDelegate<T1, T2, T3> implements TriFunction<T1, T2, T3, Void> {

    private final TernaryAction<T1, T2, T3> adapted;

    public TernaryActionToTernaryDelegate(TernaryAction<T1, T2, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary action to ternary delegate");
        this.adapted = adaptee;
    }

    @Override
    public Void apply(T1 first, T2 second, T3 third) {
        adapted.perform(first, second, third);
        return null;
    }
}
