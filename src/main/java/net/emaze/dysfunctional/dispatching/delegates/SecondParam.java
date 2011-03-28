package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class SecondParam<T1, T2> implements BinaryDelegate<T2, T1, T2> {

    @Override
    public T2 perform(T1 former, T2 latter) {
        return latter;
    }
}
