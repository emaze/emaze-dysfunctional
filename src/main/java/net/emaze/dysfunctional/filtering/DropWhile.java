package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class DropWhile<T> implements Predicate<T> {

    private boolean takeElement;
    private final Predicate<T> dropWhile;

    public DropWhile(Predicate<T> dropWhile) {
        this.dropWhile = dropWhile;
    }
      
    @Override
    public boolean accept(T element) {
        if (takeElement == false) {
            // first time and until predicate is true
            takeElement = !dropWhile.accept(element);
        }
        return takeElement;
    }
}
