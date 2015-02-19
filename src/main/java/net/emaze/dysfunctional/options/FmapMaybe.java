package net.emaze.dysfunctional.options;

import java.util.Optional;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Performs map on a {@literal Optional<T>}.
 *
 * @param <T> the resulting maybe type parameter
 * @param <R> the source maybe type parameter
 * @author rferranti
 */
public class FmapMaybe<T, R> implements Function<Optional<T>, Optional<R>> {

    private final Function<T, R> delegate;

    public FmapMaybe(Function<T, R> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapMaybe with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public Optional<R> apply(Optional<T> from) {
        dbc.precondition(from != null, "cannot fmap a null maybe");
        return from.map(delegate);
    }
}
