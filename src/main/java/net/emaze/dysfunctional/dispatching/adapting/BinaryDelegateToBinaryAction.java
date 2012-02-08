package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 * Adapts a binary delegate to a binary action. Adapting is performed by
 * ignoring result of the adapted delegate.
 *
 * @author rferranti
 * @param <R> the adapted delegate result type
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 */
public class BinaryDelegateToBinaryAction<R, T1, T2> implements BinaryAction<T1, T2> {

    private final BinaryDelegate<R, T1, T2> adapted;

    public BinaryDelegateToBinaryAction(BinaryDelegate<R, T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary delegate to binary action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T1 first, T2 second) {
        adapted.perform(first, second);
    }
}
