package net.emaze.dysfunctional.collections;

import java.util.Collection;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class CollectionAdder<T> implements Delegate<T, T> {

    private final Collection<T> collection;

    public CollectionAdder(Collection<T> collection) {
        dbc.precondition(collection != null, "cannot create a CollectionAdder with a null collection");
        this.collection = collection;
    }

    @Override
    public T perform(T element) {
        collection.add(element);
        return element;
    }
}
