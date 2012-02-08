package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary delegate yielding the second passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type, the result type
 * @param <T3> the third parameter type
 * @author rferranti
 */
public class SecondParamOfThree<T1, T2, T3> implements TernaryDelegate<T2, T1, T2, T3> {

    @Override
    public T2 perform(T1 first, T2 second, T3 third) {
        return second;
    }
}
