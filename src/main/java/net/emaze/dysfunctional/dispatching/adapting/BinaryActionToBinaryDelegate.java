package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class BinaryActionToBinaryDelegate<T1, T2> implements BinaryDelegate<Void, T1, T2> {

    private final BinaryAction<T1, T2> adapted;

    public BinaryActionToBinaryDelegate(BinaryAction<T1, T2> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null action");
        this.adapted = adapted;
    }

    @Override
    public Void perform(T1 first, T2 second) {
        adapted.perform(first, second);
        return null;
    }
}
