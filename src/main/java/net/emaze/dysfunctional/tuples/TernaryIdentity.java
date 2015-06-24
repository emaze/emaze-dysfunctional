package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Returns the three formal parameters (in a triple) passed to the function.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class TernaryIdentity<T1, T2, T3> implements TriFunction<T1, T2, T3, Triple<T1, T2, T3>> {

    @Override
    public Triple<T1, T2, T3> apply(T1 first, T2 second, T3 third) {
        return Triple.of(first, second, third);
    }
}
