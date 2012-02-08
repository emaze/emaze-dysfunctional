package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * A stateful predicate yielding true until the first time the predicate doesn't
 * matches, false when and after the first time the predicate matches.
 *
 * @param <T> the predicate element Type
 * @author rferranti
 */
public class TakeWhile<T> implements Predicate<T> {

    private boolean dropElement;
    private final Predicate<T> takeWhile;

    public TakeWhile(Predicate<T> dropWhile) {
        this.takeWhile = dropWhile;
    }

    @Override
    public boolean accept(T element) {
        if (dropElement == false) {
            // first time and until predicate is true
            dropElement = !takeWhile.accept(element);
        }
        return !dropElement;
    }
}
