package net.emaze.disfunctional.iterations;

import java.util.Iterator;
import net.emaze.disfunctional.Maybe;

/**
 *
 * @author rferranti
 */
public class OneTimeIterable<T> implements Iterable<T> {

    private Maybe<Iterator<T>> maybeIterator;

    public OneTimeIterable(Iterator<T> iterator) {
        this.maybeIterator = Maybe.just(iterator);
    }

    public Iterator<T> iterator() {
        if (!maybeIterator.hasValue()) {
            throw new IllegalStateException("consuming an iterator two times");
        }
        final Iterator<T> iterator = maybeIterator.value();
        maybeIterator = Maybe.nothing();
        return iterator;
    }
}
