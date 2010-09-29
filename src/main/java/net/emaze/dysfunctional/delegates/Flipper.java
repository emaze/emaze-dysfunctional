package net.emaze.dysfunctional.delegates;

/**
 * BinaryDelegate<R,U,T> to BinaryDelegate<R,T,U> adapter
 * (just swaps formal params)
 * == No dolphins were harmed during the making of this functor ==
 * @param <R> return type of the given binary delegate
 * @param <T> former formal parameter type of the given delegate
 * @param <U> latter formal parameter type of the given delegate
 * @author rferranti
 */
public class Flipper<R, T, U> implements BinaryDelegate<R, T, U> {

    private final BinaryDelegate<R, U, T> delegate;

    public Flipper(BinaryDelegate<R, U, T> delegate) {
        this.delegate = delegate;
    }

    /**
     * performs on the nested delegate swapping former and latter formal parameters
     * @param former the former formal parameter used as latter in the nested delegate
     * @param latter the latter formal parameter used as former in the nested delegate
     * @return
     */
    @Override
    public R perform(T former, U latter) {
        return delegate.perform(latter, former);
    }
}
