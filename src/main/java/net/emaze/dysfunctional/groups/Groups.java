package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 *
 * @author rferranti
 */
public abstract class Groups {

    public static <K, V> Map<K, List<V>> groupBy(Iterable<V> groupies, Delegate<K, V> grouper) {
        final HashMapFactory<K, List<V>> hashMapFactory = new HashMapFactory<K, List<V>>();
        final Provider<List<V>> provider = Dispatching.compose(new Narrow<List<V>, ArrayList<V>>(), new ArrayListFactory<V>());
        return groupBy(groupies, grouper, provider, hashMapFactory);
    }

    public static <C extends Collection<V>, K, V> Map<K, C> groupBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider) {
        return groupBy(groupies, grouper, collectionProvider, new HashMapFactory<K, C>());
    }

    public static <VC extends Collection<V>, M extends Map<K, VC>, C extends VC, K, V> Map<K, VC> groupBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot group with a null iterable");
        dbc.precondition(grouper != null, "cannot group with a null grouper");
        dbc.precondition(collectionProvider != null, "cannot group with a null collectionProvider");
        dbc.precondition(mapProvider != null, "cannot group with a null mapProvider");
        final M grouped = mapProvider.provide();
        for (V groupie : groupies) {
            final K group = grouper.perform(groupie);
            if (!grouped.containsKey(group)) {
                grouped.put(group, collectionProvider.provide());
            }
            grouped.get(group).add(groupie);
        }
        return grouped;
    }
}
