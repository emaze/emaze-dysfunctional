package net.emaze.dysfunctional.collections;

import java.util.HashMap;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 *
 * @author rferranti
 */
public class HashMapFactory<K, V> implements Provider<HashMap<K, V>> {

    @Override
    public HashMap<K, V> provide() {
        return new HashMap<K, V>();
    }
}
