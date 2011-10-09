package net.emaze.dysfunctional.collections;

import java.util.Collection;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * An endodelegate adding all elements from a collection to a collection.
 * @param <C> the collection type parameter
 * @param <E> the element type parameter
 * @author rferranti
 */
public class CollectionAllAdder<C extends Collection<E>, E> implements Delegate<C, C> {

    private final Collection<E> collection;

    public CollectionAllAdder(Collection<E> collection) {
        dbc.precondition(collection != null, "cannot create a CollectionAllAdder with a null collection");
        this.collection = collection;
    }

    @Override
    public C perform(C elements) {
        collection.addAll(elements);
        return elements;
    }
}
