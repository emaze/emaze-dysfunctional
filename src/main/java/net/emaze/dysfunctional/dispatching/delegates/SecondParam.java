package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A binary delegate yielding second passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type, the result type
 * @author rferranti
 */
public class SecondParam<T1, T2> implements BinaryDelegate<T2, T1, T2> {

    @Override
    public T2 perform(T1 former, T2 latter) {
        return latter;
    }
}
