package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary functor
 * @param <R> the result Type
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element Type
 * @author rferranti
 */
public interface TernaryDelegate<R, T1, T2, T3> {

    /**
     * Executes the delegate for the given elements yielding a result of type R
     * @param first the first element
     * @param second the second element
     * @param third the third element
     * @return the result
     */
    R perform(T1 first, T2 second, T3 third);
}
