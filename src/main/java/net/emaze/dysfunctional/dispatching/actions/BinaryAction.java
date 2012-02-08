package net.emaze.dysfunctional.dispatching.actions;

/**
 * A Binary functor with no return value.
 * @param <E1> the former element type parameter
 * @param <E2> the latter element type parameter
 * @author rferranti
 */
public interface BinaryAction<E1,E2> {
    /**
     * Performs an action for the given elements.
     * @param former the former element
     * @param latter the latter element
     */
    void perform(E1 former, E2 latter);
}
