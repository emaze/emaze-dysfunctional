package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;

/**
 * transforms an Iterator<T> in a MaybeIterator<T>
 * @author rferranti
 * @param <E>
 * @param <T>
 */
public class MaybeIteratorTransformer<T extends Iterator<E>, E> implements Delegate<Iterator<Maybe<E>>, T> {

    @Override
    public MaybeIterator<E> perform(T iterator) {
        dbc.precondition(iterator != null, "cannot transform a null iterator to a MaybeIterator");
        return new MaybeIterator<E>(iterator);
    }
}
