package net.emaze.dysfunctional.dispatching.delegates;

/**
 *
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class SecondParamOfThree<T1, T2, T3> implements TernaryDelegate<T2, T1, T2, T3> {

    @Override
    public T2 perform(T1 first, T2 second, T3 third) {
        return second;
    }
}
