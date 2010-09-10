package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.Maybe;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class OneTimeIterable<T> implements Iterable<T> {
    private Maybe<Iterator<T>> maybeIterator;

    public OneTimeIterable(Iterator<T> iterator) {
        this.maybeIterator = Maybe.just(iterator);
    }

    @Override
    public Iterator<T> iterator() {
        dbc.invariant(maybeIterator.hasValue(), "consuming an iterator two times");
        final Iterator<T> iterator = maybeIterator.value();
        maybeIterator = Maybe.nothing();
        return iterator;
    }
}
