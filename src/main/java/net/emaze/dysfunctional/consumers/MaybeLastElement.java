package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.Optional;
import net.emaze.dysfunctional.options.OptionalIterator;

/**
 * gives up only after consuming the last element (and returns it)
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class MaybeLastElement<E> implements Function<Iterator<E>, Optional<E>> {

    @Override
    public Optional<E> apply(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        final Iterator<Optional<E>> maybeIter = new OptionalIterator<E>(iterator);
        Optional<E> value = maybeIter.next();
        while (iterator.hasNext()) {
            value = maybeIter.next();
        }
        return value;
    }
}
