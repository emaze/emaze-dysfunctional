package net.emaze.dysfunctional.delegates;

/**
 * Binary to unary delegate adapter (lcurry)
 * @param <R>
 * @param <T>
 * @param <U>
 * @author rferranti
 */
public class BinderFirst<R, T, U> implements Delegate<R, U> {

    private final BinaryDelegate<R, T, U> delegate;
    private final T first;

    public BinderFirst(BinaryDelegate<R, T, U> delegate, T first) {
        this.delegate = delegate;
        this.first = first;
    }

    @Override
    public R perform(U second) {
        return delegate.perform(first, second);
    }
}
