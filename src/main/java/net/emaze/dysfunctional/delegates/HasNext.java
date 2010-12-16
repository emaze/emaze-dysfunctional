package net.emaze.dysfunctional.delegates;

import java.util.Iterator;

/**
 * Unary predicate matching iterators having a next value
 * @param <T> 
 * @author rferranti
 */
public class HasNext<T extends Iterator<?>> implements Predicate<T> {

    @Override
    public boolean test(T iterator) {
        return iterator.hasNext();
    }
}
