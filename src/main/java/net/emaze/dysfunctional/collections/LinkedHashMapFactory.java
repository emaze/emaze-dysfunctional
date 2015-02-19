package net.emaze.dysfunctional.collections;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

/**
 * Creates an empty LinkedHashMap.
 *
 * @param <K> the LinkedHashMap key type parameter
 * @param <V> the LinkedHashMap value type parameter
 * @author rferranti
 */
public class LinkedHashMapFactory<K, V> implements Supplier<LinkedHashMap<K, V>> {

    @Override
    public LinkedHashMap<K, V> get() {
        return new LinkedHashMap<K, V>();
    }
}
