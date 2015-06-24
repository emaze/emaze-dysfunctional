package net.emaze.dysfunctional.dispatching.actions;

import java.util.function.Consumer;

/**
 * A null unary functor with no return value effectively doing nothing.
 *
 * @param <E> the element type
 * @author rferranti
 */
public class Noop<E> implements Consumer<E> {

    /**
     * Does nothing ignoring the parameter.
     *
     * @param element the element we ignore to do nothing
     */
    @Override
    public void accept(E element) {
    }
}
