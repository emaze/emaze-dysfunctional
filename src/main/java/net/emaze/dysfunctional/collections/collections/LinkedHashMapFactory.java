package net.emaze.dysfunctional.collections;

import java.util.LinkedHashMap;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Creates an empty LinkedHashMap.
 * @param <K> the LinkedHashMap key type parameter
 * @param <V> the LinkedHashMap value type parameter
 * @author rferranti
 */
public class LinkedHashMapFactory<K, V> implements Provider<LinkedHashMap<K, V>> {

    @Override
    public LinkedHashMap<K, V> provide() {
        return new LinkedHashMap<K, V>();
    }
}
