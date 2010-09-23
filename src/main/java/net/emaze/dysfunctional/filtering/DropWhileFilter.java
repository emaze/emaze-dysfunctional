package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class DropWhileFilter<T> implements Predicate<T> {

    private boolean takeElement;
    private final Predicate<T> dropWhile;

    public DropWhileFilter(Predicate<T> dropWhile) {
        this.dropWhile = dropWhile;
    }
      
    @Override
    public boolean test(T element) {
        if (takeElement == false) {
            // first time and until predicate is true
            takeElement = !dropWhile.test(element);
        }
        return takeElement;
    }
}
