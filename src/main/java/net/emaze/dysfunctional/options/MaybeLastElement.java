package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * gives up only after consuming the last element (and returns it)
 *
 * @param <E>
 * @author rferranti
 */
public class MaybeLastElement<E> implements Delegate<Maybe<E>, Iterator<E>> {

    @Override
    public Maybe<E> perform(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "consuming a null iterator");
        final Iterator<Maybe<E>> maybeIter = new MaybeIterator<E>(iterator);
        Maybe<E> value = maybeIter.next();
        while (iterator.hasNext()) {
            value = maybeIter.next();
        }
        return value;
    }
}
