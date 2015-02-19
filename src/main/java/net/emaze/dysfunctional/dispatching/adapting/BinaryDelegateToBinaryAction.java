package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import java.util.function.BiFunction;

/**
 * Adapts a binary delegate to a binary action. Adapting is performed by
 * ignoring result of the adapted delegate.
 *
 * @author rferranti
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <R> the adapted delegate result type
 */
public class BinaryDelegateToBinaryAction<T1, T2, R> implements BinaryAction<T1, T2> {

    private final BiFunction<T1, T2, R> adapted;

    public BinaryDelegateToBinaryAction(BiFunction<T1, T2, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary delegate to binary action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T1 first, T2 second) {
        adapted.apply(first, second);
    }
}
