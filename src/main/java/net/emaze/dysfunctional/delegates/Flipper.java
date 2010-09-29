package net.emaze.dysfunctional.delegates;

/**
 * BinaryDelegate<R,U,T> to BinaryDelegate<R,T,U> adapter
 * (just swaps formal params)
 * == No dolphins were harmed during the making of this functor ==
 * @param <R>
 * @param <T>
 * @param <U> 
 * @author rferranti
 */
public class Flipper<R, T, U> implements BinaryDelegate<R, T, U> {

    private final BinaryDelegate<R, U, T> delegate;

    public Flipper(BinaryDelegate<R, U, T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public R perform(T former, U latter) {
        return delegate.perform(latter, former);
    }
}
