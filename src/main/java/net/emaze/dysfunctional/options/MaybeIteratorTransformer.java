package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * transforms an Iterator<T> in a MaybeIterator<T>
 *
 * @author rferranti
 * @param <E> the iterator element type
 * @param <I> the iterator type
 */
public class MaybeIteratorTransformer<I extends Iterator<E>, E> implements Delegate<Iterator<Maybe<E>>, I> {

    @Override
    public MaybeIterator<E> perform(I iterator) {
        dbc.precondition(iterator != null, "cannot transform a null iterator to a MaybeIterator");
        return new MaybeIterator<E>(iterator);
    }
}
