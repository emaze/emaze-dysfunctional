package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.Optional;
import net.emaze.dysfunctional.options.OptionalIterator;

/**
 * Searches the first element.
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class MaybeFirstElement<E> implements Function<Iterator<E>, Optional<E>> {

    @Override
    public Optional<E> apply(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        return new OptionalIterator<E>(consumable).next();
    }
}
