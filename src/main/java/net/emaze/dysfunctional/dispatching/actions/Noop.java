package net.emaze.dysfunctional.dispatching.actions;

/**
 * A null unary functor with no return value effectively doing nothing.
 *
 * @param <E> the element type
 * @author rferranti
 */
public class Noop<E> implements Action<E> {

    /**
     * Does nothing ignoring the parameter.
     *
     * @param element the element we ignore to do nothing
     */
    @Override
    public void perform(E element) {
    }
}
