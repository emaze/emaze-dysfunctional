package net.emaze.dysfunctional.delegates;

/**
 * Binary to unary delegate adapter (rcurry)
 * @param <R> the return Type
 * @param <T> the former element Type
 * @param <U> the latter element Type
 * @author rferranti
 */
public class BinderSecond<R, T, U> implements Delegate<R, T> {

    private final BinaryDelegate<R, T, U> delegate;
    private final U second;

    public BinderSecond(BinaryDelegate<R, T, U> delegate, U second) {
        this.delegate = delegate;
        this.second = second;
    }

    @Override
    public R perform(T first) {
        return delegate.perform(first, second);
    }
}
