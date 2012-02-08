package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A binary delegate yielding the first passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @author rferranti
 */
public class FirstParam<T1, T2> implements BinaryDelegate<T1, T1, T2> {

    @Override
    public T1 perform(T1 former, T2 latter) {
        return former;
    }
}
