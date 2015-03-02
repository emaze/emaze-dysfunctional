package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary functor.
 *
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element Type
 * @param <R> the result Type
 */
@FunctionalInterface
public interface TriFunction<T1, T2, T3, R> {

    /**
     * Executes the function for the given elements yielding a result of type R
     *
     * @param first the first element
     * @param second the second element
     * @param third the third element
     * @return the result
     */
    R apply(T1 first, T2 second, T3 third);
}
