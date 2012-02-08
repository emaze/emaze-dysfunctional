package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Performs fmap on a {@literal Maybe<T>}.
 *
 * @param <R> the source maybe type parameter
 * @param <T> the resulting maybe type parameter
 * @author rferranti
 */
public class FmapMaybe<R, T> implements Delegate<Maybe<R>, Maybe<T>> {

    private final Delegate<R, T> delegate;

    public FmapMaybe(Delegate<R, T> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapMaybe with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public Maybe<R> perform(Maybe<T> from) {
        dbc.precondition(from != null, "cannot fmap a null maybe");
        return from.fmap(delegate);
    }
}
