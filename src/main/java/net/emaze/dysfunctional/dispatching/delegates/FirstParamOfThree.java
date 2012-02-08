package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary delegate yielding the first passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 * @author rferranti
 */
public class FirstParamOfThree<T1, T2, T3> implements TernaryDelegate<T1, T1, T2, T3> {

    @Override
    public T1 perform(T1 first, T2 second, T3 third) {
        return first;
    }
}
