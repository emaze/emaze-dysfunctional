package net.emaze.dysfunctional.collections.builders;

import java.util.Collections;
import java.util.Map;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A map builder.
 *
 * @author rferranti
 * @param <K> the map key type
 * @param <V> the map value type
 */
public class MapBuilder<K, V> {

    private final Map<K, V> state;

    public MapBuilder(Map<K, V> state) {
        dbc.precondition(state != null, "cannot create a MapBuilder with a null state");
        this.state = state;
    }

    public MapBuilder<K, V> add(K key, V value) {
        state.put(key, value);
        return this;
    }

    public MapBuilder<K, V> add(Map<K, ? extends V> map) {
        dbc.precondition(map != null, "cannot merge a null map");
        state.putAll(map);
        return this;
    }

    public Map<K, V> toMap() {
        return state;
    }
    
    public Map<K, V> toUnmodifiableMap() {
        return Collections.unmodifiableMap(state);
    }
}
