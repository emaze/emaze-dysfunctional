package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.delegates.BinaryDelegate;

/**
 * Returns both formal parameters (in a pair) passed to the delegate.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @author rferranti
 */
public class BinaryIdentity<T1, T2> implements BinaryDelegate<Pair<T1, T2>, T1, T2> {

    @Override
    public Pair<T1, T2> perform(T1 former, T2 latter) {
        return Pair.of(former, latter);
    }
}
