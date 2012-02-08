package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Binary to unary delegate adapter. Adapting is performed by currying the
 * second parameter of the adapted binary delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T> the adapted delegate former parameter type
 * @param <U> the adapted delegate latter parameter type
 * @author rferranti
 */
public class BinderSecond<R, T, U> implements Delegate<R, T> {

    private final BinaryDelegate<R, T, U> adapted;
    private final U second;

    public BinderSecond(BinaryDelegate<R, T, U> adaptee, U second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null binary delegate");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public R perform(T first) {
        return adapted.perform(first, second);
    }
}
