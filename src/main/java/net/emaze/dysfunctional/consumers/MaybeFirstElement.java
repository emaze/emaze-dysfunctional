package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * Searches the first element.
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class MaybeFirstElement<E> implements Function<Iterator<E>, Maybe<E>> {

    @Override
    public Maybe<E> apply(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        return new MaybeIterator<E>(consumable).next();
    }
}
