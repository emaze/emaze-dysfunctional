package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.Predicate;

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
    public boolean test(T element) {
        return element != null;
    }
}
