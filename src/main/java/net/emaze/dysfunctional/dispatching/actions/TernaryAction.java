package net.emaze.dysfunctional.dispatching.actions;

/**
 * A ternary functor with no return value.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 * @author rferranti
 */
public interface TernaryAction<T1, T2, T3> {

    /**
     * Performs an action for the given elements.
     *
     * @param first the first element
     * @param second the second element
     * @param third the third element
     */
    void perform(T1 first, T2 second, T3 third);
}
