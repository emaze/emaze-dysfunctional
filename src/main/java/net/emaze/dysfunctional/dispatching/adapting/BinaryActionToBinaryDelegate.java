package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 * Adapts a binary action to a binary delegate. Adapter result type is Void and
 * always yields null.
 *
 * @param <T1> the adapted action first parameter type
 * @param <T2> the adapted action second parameter type
 * @author rferranti
 */
public class BinaryActionToBinaryDelegate<T1, T2> implements BinaryDelegate<Void, T1, T2> {

    private final BinaryAction<T1, T2> adapted;

    public BinaryActionToBinaryDelegate(BinaryAction<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary action to a binary delegate");
        this.adapted = adaptee;
    }

    @Override
    public Void perform(T1 first, T2 second) {
        adapted.perform(first, second);
        return null;
    }
}
