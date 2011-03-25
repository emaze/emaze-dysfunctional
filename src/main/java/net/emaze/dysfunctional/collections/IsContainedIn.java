package net.emaze.dysfunctional.collections;

import java.util.Collection;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @author rferranti
 */
public class IsContainedIn<T> implements Predicate<T> {

    private final Collection<T> collection;

    public IsContainedIn(Collection<T> collection) {
        dbc.precondition(collection != null, "cannot create IsContainedIn predicate with a null collection");
        this.collection = collection;
    }

    @Override
    public boolean test(T element) {
        return collection.contains(element);
    }
}
