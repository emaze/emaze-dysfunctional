package net.emaze.dysfunctional.tuples;

import java.util.function.BiFunction;

/**
 * Returns both formal parameters (in a pair) passed to the function.
 *
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @author rferranti
 */
public class BinaryIdentity<T1, T2> implements BiFunction<T1, T2, Pair<T1, T2>> {

    @Override
    public Pair<T1, T2> apply(T1 former, T2 latter) {
        return Pair.of(former, latter);
    }
}
