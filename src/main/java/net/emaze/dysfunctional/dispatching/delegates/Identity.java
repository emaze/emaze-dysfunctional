package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A unary delegate yielding the passed parameter.
 *
 * \mathds{1}_{T}
 *
 * @param <T> the parameter and result type
 * @author rferranti
 */
public class Identity<T> implements Delegate<T, T> {

    /**
     * Yields the passed parameter.
     *
     * @param element the given element
     * @return the given element
     */
    @Override
    public T perform(T element) {
        return element;
    }
}
