package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Performs fmap on a {@literal Box<T>}.
 *
 * @param <R> the resulting box type parameter
 * @param <T> the source box type parameter
 * @author rferranti
 */
public class FmapBox<R, T> implements Delegate<Box<R>, Box<T>> {

    private final Delegate<R, T> delegate;

    public FmapBox(Delegate<R, T> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapBox with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public Box<R> perform(Box<T> from) {
        dbc.precondition(from != null, "cannot fmap a null box");
        return from.fmap(delegate);
    }
}
