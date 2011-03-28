package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class ThirdParam<T1, T2, T3> implements TernaryDelegate<T3, T1, T2, T3> {

    @Override
    public T3 perform(T1 first, T2 second, T3 third) {
        return third;
    }
}
