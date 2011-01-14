package net.emaze.dysfunctional.collections;

import java.util.Collection;

/**
 * Contract to create a collection.
 * @param <C> the collection type parameter
 * @param <E> the collection element type parameter
 * @author rferranti
 */
public interface CollectionFactory<C extends Collection<E>, E> {

    /**
     * Creates a collection.
     * @return the created collection
     */
    public C create();
}
