package net.emaze.dysfunctional.dispatching.adapting;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Maybe;

/**
 * Adapts an {@code Iterator<T>} to a {@code Provider<Maybe<T>>}. This is a
 * stateful provider yielding Just elements of the source iterator, and Nothing
 * when the iterator is consumed.
 *
 * @author rferranti
 */
public class IteratingProvider<T> implements Provider<Maybe<T>> {

    private final Iterator<T> iterator;

    public IteratingProvider(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot adapt a null iterator");
        this.iterator = iterator;
    }

    @Override
    public Maybe<T> provide() {
        return iterator.hasNext() ? Maybe.just(iterator.next()) : Maybe.<T>nothing();
    }
}
