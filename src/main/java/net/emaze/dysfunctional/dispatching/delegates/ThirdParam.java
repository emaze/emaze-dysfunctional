package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary delegate yielding third passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type, the result type
 * @author rferranti
 */
public class ThirdParam<T1, T2, T3> implements TernaryDelegate<T3, T1, T2, T3> {

    @Override
    public T3 perform(T1 first, T2 second, T3 third) {
        return third;
    }
}
