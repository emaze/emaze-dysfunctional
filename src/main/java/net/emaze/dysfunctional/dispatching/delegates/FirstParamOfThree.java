package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary function yielding the first passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 * @author rferranti
 */
public class FirstParamOfThree<T1, T2, T3> implements TriFunction<T1, T2, T3, T1> {

    @Override
    public T1 apply(T1 first, T2 second, T3 third) {
        return first;
    }
}
