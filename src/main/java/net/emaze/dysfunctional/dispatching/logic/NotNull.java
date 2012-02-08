package net.emaze.dysfunctional.dispatching.logic;

/**
 * A Predicate matching nonnull elements.
 *
 * @param <T> the element type
 * @author dangelocola
 */
public class NotNull<T> implements Predicate<T> {

    /**
     * Yields true if the element is not null.
     *
     * @param element the element to be evaluated
     * @return true if the element is not null.
     */
    @Override
    public boolean accept(T element) {
        return element != null;
    }
}
