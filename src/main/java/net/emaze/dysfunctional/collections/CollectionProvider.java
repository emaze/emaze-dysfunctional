package net.emaze.dysfunctional.collections;

import java.util.Collection;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Provides the collection passed in construction.
 * @param <C> the collection type parameter
 * @param <E> the collection element type parameter
 * @author rferranti
 */
public class CollectionProvider<C extends Collection<E>, E> implements Provider<C> {

    private final C collection;

    public CollectionProvider(C collection) {
        dbc.precondition(collection != null, "cannot create a collection provider with a null collection");
        this.collection = collection;
    }

    @Override
    public C provide() {
        return collection;
    }
}
