package net.emaze.dysfunctional.collections;

import java.util.Comparator;
import java.util.TreeMap;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Creates an empty TreeMap.
 * @param <K> the TreeMap key type parameter
 * @param <V> the TreeMap value type parameter
 * @author rferranti
 */
public class TreeMapFactory<K, V> implements Provider<TreeMap<K, V>> {

    private final Comparator<K> comparator;

    public TreeMapFactory(Comparator<K> comparator) {
        dbc.precondition(comparator != null, "cannot create a TreeMapFactory with a null comparator");
        this.comparator = comparator;
    }
    
    @Override
    public TreeMap<K, V> provide() {
        return new TreeMap<K, V>(comparator);
    }
}
