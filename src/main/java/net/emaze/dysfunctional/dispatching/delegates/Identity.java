package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;

/**
 * A unary delegate yielding the passed parameter.
 *
 * \mathds{1}_{T}
 *
 * @param <T> the parameter and result type
 * @author rferranti
 */
public class Identity<T> implements Function<T, T> {

    /**
     * Yields the passed parameter.
     *
     * @param element the given element
     * @return the given element
     */
    @Override
    public T apply(T element) {
        return element;
    }
}
