package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;

/**
 * A binary function yielding second passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type, the result type
 * @author rferranti
 */
public class SecondParam<T1, T2> implements BiFunction<T1, T2, T2> {

    @Override
    public T2 apply(T1 former, T2 latter) {
        return latter;
    }
}
