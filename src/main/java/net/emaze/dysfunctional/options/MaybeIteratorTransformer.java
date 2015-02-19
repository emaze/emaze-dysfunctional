package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * transforms an Iterator<T> in a MaybeIterator<T>
 *
 * @author rferranti
 * @param <E> the iterator element type
 * @param <I> the iterator type
 */
public class MaybeIteratorTransformer<I extends Iterator<E>, E> implements Function<I, Iterator<Maybe<E>>> {

    @Override
    public MaybeIterator<E> apply(I iterator) {
        dbc.precondition(iterator != null, "cannot transform a null iterator to a MaybeIterator");
        return new MaybeIterator<E>(iterator);
    }
}
