package net.emaze.dysfunctional.collections;

import java.util.Collection;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * An endodelegate adding element to a collection.
 * @param <T> the element type
 * @author rferranti
 */
public class CollectionAdder<T> implements UnaryOperator<T> {

    private final Collection<T> collection;

    public CollectionAdder(Collection<T> collection) {
        dbc.precondition(collection != null, "cannot create a CollectionAdder with a null collection");
        this.collection = collection;
    }

    @Override
    public T apply(T element) {
        collection.add(element);
        return element;
    }
}
