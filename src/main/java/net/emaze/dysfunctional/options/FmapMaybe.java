package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Performs fmap on a {@literal Maybe<T>}.
 *
 * @param <T> the resulting maybe type parameter
 * @param <R> the source maybe type parameter
 * @author rferranti
 */
public class FmapMaybe<T, R> implements Function<Maybe<T>, Maybe<R>> {

    private final Function<T, R> delegate;

    public FmapMaybe(Function<T, R> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapMaybe with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public Maybe<R> apply(Maybe<T> from) {
        dbc.precondition(from != null, "cannot fmap a null maybe");
        return from.fmap(delegate);
    }
}
