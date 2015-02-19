package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * gives up only after consuming the last element (and returns it)
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class MaybeLastElement<E> implements Function<Iterator<E>, Maybe<E>> {

    @Override
    public Maybe<E> apply(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        final Iterator<Maybe<E>> maybeIter = new MaybeIterator<E>(iterator);
        Maybe<E> value = maybeIter.next();
        while (iterator.hasNext()) {
            value = maybeIter.next();
        }
        return value;
    }
}
