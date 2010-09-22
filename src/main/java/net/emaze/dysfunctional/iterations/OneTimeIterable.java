package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts an Iterator to the Iterable interface enabling the iterator to be consumed
 * where an iterable is expected (a OneTimeIterable can be consumed ONLY ONCE )
 * @author rferranti
 */
public class OneTimeIterable<T> implements Iterable<T> {
    private Maybe<Iterator<T>> maybeIterator;

    public OneTimeIterable(Iterator<T> iterator) {
        this.maybeIterator = Maybe.just(iterator);
    }

    @Override
    public Iterator<T> iterator() {
        dbc.stateprecondition(maybeIterator.hasValue(), "consuming an iterator two times");
        final Iterator<T> iterator = maybeIterator.value();
        maybeIterator = Maybe.nothing();
        return iterator;
    }
}
