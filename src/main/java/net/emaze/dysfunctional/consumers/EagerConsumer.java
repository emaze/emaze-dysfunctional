package net.emaze.dysfunctional.consumers;

import java.util.Collection;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Provider;

/**
 * Consumes every element from the consumable into the collection provided by
 * the provider
 * @param <R> the collection type parameter
 * @param <E> the collection element type parameter
 * @author rferranti
 */
public class EagerConsumer<R extends Collection<E>, E> implements Consumer<R, Iterator<E>> {

    private final Provider<R> provider;

    public EagerConsumer(Provider<R> provider) {
        dbc.precondition(provider != null, "collection factory cannot be null");
        this.provider = provider;
    }

    @Override
    public R consume(Iterator<E> consumable) {
        dbc.precondition(consumable != null, "consuming a null iterator");
        final R out = provider.provide();
        while (consumable.hasNext()) {
            out.add(consumable.next());
        }
        return out;
    }
}
