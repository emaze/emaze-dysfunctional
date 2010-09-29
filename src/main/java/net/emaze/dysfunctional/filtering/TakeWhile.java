package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class TakeWhile<T> implements Predicate<T> {

    private boolean dropElement;
    private final Predicate<T> takeWhile;

    public TakeWhile(Predicate<T> dropWhile) {
        this.takeWhile = dropWhile;
    }
      
    @Override
    public boolean test(T element) {
        if (dropElement == false) {
            // first time and until predicate is true
            dropElement = !takeWhile.test(element);
        }
        return !dropElement;
    }
}
