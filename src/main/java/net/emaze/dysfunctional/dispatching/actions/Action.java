package net.emaze.dysfunctional.dispatching.actions;

/**
 * A unary functor with no return value
 * @param <E>
 * @author rferranti
 */
public interface Action<E> {

    /**
     * Performs an action for the given element
     * @param element the element
     */
    public void perform(E element);
}
