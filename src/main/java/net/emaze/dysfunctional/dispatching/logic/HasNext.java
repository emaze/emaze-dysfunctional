package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.Predicate;
import java.util.Iterator;

/**
 * Unary predicate matching iterators having a next value.
 *
 * @param <I> the iterator type
 * @author rferranti
 */
public class HasNext<I extends Iterator<?>> implements Predicate<I> {

    @Override
    public boolean test(I iterator) {
        return iterator.hasNext();
    }
}
