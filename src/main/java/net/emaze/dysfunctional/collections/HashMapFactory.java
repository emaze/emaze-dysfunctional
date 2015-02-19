package net.emaze.dysfunctional.collections;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Creates an empty HashMap.
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author rferranti
 */
public class HashMapFactory<K, V> implements Supplier<HashMap<K, V>> {

    @Override
    public HashMap<K, V> get() {
        return new HashMap<K, V>();
    }
}
