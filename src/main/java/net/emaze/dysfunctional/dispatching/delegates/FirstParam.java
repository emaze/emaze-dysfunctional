package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class FirstParam<T1, T2> implements BinaryDelegate<T1, T1, T2> {

    @Override
    public T1 perform(T1 former, T2 latter) {
        return former;
    }
    
}
