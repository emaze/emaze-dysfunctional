package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

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

    /**
     * Returns a composed function that first applies this function to its input, and then
     * applies the {@code after} function to the result. If
     *
     * @param <V> the type of output of the {@code after} function, and of the composed
     * function
     * @param after the function to apply after this function is applied
     * @return the composed function
     */
    default <V> TriFunction<T1, T2, T3, V> andThen(Function<? super R, ? extends V> after) {
        dbc.precondition(after != null, "cannot compose a ternary function with a null function");
        return (first, second, third) -> after.apply(apply(first, second, third));
    }
}
