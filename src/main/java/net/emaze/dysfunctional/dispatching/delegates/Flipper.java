package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a binary delegate to another binary delegate by swapping formal
 * parameters.
 *
 * == No dolphins were harmed during the making of this functor ==
 *
 * @param <T> former formal parameter type of the given delegate
 * @param <U> latter formal parameter type of the given delegate
 * @param <R> return type of the given binary delegate
 * @author rferranti
 */
public class Flipper<T, U, R> implements BiFunction<T, U, R> {

    private final BiFunction<U, T, R> delegate;

    public Flipper(BiFunction<U, T, R> delegate) {
        dbc.precondition(delegate != null, "cannot flip a null binary delegate");
        this.delegate = delegate;
    }

    /**
     * Performs on the nested delegate swapping former and latter formal
     * parameters.
     *
     * @param former the former formal parameter used as latter in the nested
     * delegate
     * @param latter the latter formal parameter used as former in the nested
     * delegate
     * @return
     */
    @Override
    public R apply(T former, U latter) {
        return delegate.apply(latter, former);
    }
}
