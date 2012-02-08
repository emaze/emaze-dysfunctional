package net.emaze.dysfunctional.dispatching.actions;

/**
 * A null binary functor with no return value effectively doing nothing. Much
 * better than {@literal Noop<T>}.
 *
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class BinaryNoop<T1, T2> implements BinaryAction<T1, T2> {

    /**
     * Does nothing, ignoring parameters.
     *
     * @param former the first parameter
     * @param latter the second parameter
     */
    @Override
    public void perform(T1 former, T2 latter) {
    }
}
