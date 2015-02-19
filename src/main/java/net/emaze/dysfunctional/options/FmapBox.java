package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Performs fmap on a {@literal Box<T>}.
 *
 * @param <R> the resulting box type parameter
 * @param <T> the source box type parameter
 * @author rferranti
 */
public class FmapBox<T, R> implements Function<Box<T>, Box<R>> {

    private final Function<T, R> delegate;

    public FmapBox(Function<T, R> delegate) {
        dbc.precondition(delegate != null, "cannot create FmapBox with a null delegate");
        this.delegate = delegate;
    }

    @Override
    public Box<R> apply(Box<T> from) {
        dbc.precondition(from != null, "cannot fmap a null box");
        return from.fmap(delegate);
    }
}
