package net.emaze.dysfunctional.groups;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 *
 * @author rferranti
 */
public class GroupBy<M extends Map<K, C>, C extends Collection<V>, K, V> implements Delegate<M, Iterator<V>> {

    private final Delegate<K, V> grouper;
    private final Provider<C> collectionProvider;
    private final Provider<M> mapProvider;

    public GroupBy(Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        dbc.precondition(grouper != null, "cannot group with a null grouper");
        dbc.precondition(collectionProvider != null, "cannot group with a null collectionProvider");
        dbc.precondition(mapProvider != null, "cannot group with a null mapProvider");
        this.grouper = grouper;
        this.collectionProvider = collectionProvider;
        this.mapProvider = mapProvider;
    }

    @Override
    public M perform(Iterator<V> groupies) {
        dbc.precondition(groupies != null, "cannot group with a null iterable");
        final M grouped = mapProvider.provide();
        while (groupies.hasNext()) {
            final V groupie = groupies.next();
            final K group = grouper.perform(groupie);
            if (!grouped.containsKey(group)) {
                grouped.put(group, collectionProvider.provide());
            }
            grouped.get(group).add(groupie);
        }
        return grouped;

    }
}
