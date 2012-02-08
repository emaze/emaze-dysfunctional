package net.emaze.dysfunctional.dispatching.actions;

/**
 * A unary functor with no return value.
 *
 * @param <E> the element type
 * @author rferranti
 */
public interface Action<E> {

    /**
     * Performs an action for the given element.
     *
     * @param element the element
     */
    void perform(E element);
}
