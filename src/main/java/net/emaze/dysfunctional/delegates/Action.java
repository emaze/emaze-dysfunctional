package net.emaze.dysfunctional.delegates;

/**
 * A unary functor with no return value
 * @author rferranti
 */
public interface Action<E> {

    /**
     * Performs an action for the given element
     * @param element the element
     */
    public void perform(E element);
}
