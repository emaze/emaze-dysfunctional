package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A ternary function yielding third passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type, the result type
 * @author rferranti
 */
public class ThirdParam<T1, T2, T3> implements TriFunction<T1, T2, T3, T3> {

    @Override
    public T3 apply(T1 first, T2 second, T3 third) {
        return third;
    }
}
