package net.emaze.dysfunctional.dispatching.logic;

/**
 * A Predicate matching nonnull elements
 * @param <T> the element type
 * @author dangelocola
 */
public class NotNull<T> implements Predicate<T> {

    @Override
    public boolean test(T element) {
        return element != null;
    }

}
