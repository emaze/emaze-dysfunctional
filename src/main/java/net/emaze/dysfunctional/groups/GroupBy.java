package net.emaze.dysfunctional.groups;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A unary delegate grouping elements from an iterator. Group key is provided by
 * the passed delegate.
 *
 * @author rferranti
 * @param <M> the map type
 * @param <C> the collection type
 * @param <K> the map key type
 * @param <V> the map value type
 */
public class GroupBy<M extends Map<K, C>, C extends Collection<V>, K, V> implements Function<Iterator<V>, M> {

    private final Function<V, K> grouper;
    private final Supplier<C> collectionProvider;
    private final Supplier<M> mapProvider;

    public GroupBy(Function<V, K> grouper, Supplier<C> collectionProvider, Supplier<M> mapProvider) {
        dbc.precondition(grouper != null, "cannot group with a null grouper");
        dbc.precondition(collectionProvider != null, "cannot group with a null collectionProvider");
        dbc.precondition(mapProvider != null, "cannot group with a null mapProvider");
        this.grouper = grouper;
        this.collectionProvider = collectionProvider;
        this.mapProvider = mapProvider;
    }

    @Override
    public M apply(Iterator<V> groupies) {
        dbc.precondition(groupies != null, "cannot group with a null iterator");
        final M grouped = mapProvider.get();
        while (groupies.hasNext()) {
            final V groupie = groupies.next();
            final K group = grouper.apply(groupie);
            if (!grouped.containsKey(group)) {
                grouped.put(group, collectionProvider.get());
            }
            grouped.get(group).add(groupie);
        }
        return grouped;

    }
}
