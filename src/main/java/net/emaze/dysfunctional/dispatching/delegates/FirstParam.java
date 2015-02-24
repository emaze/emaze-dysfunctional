package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;

/**
 * A binary function yielding the first passed parameter.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @author rferranti
 */
public class FirstParam<T1, T2> implements BiFunction<T1, T2, T1> {

    @Override
    public T1 apply(T1 former, T2 latter) {
        return former;
    }
}
