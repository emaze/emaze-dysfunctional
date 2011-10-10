package net.emaze.dysfunctional.collections.builders;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

public class MapTreeBuilder<K> {

    private final Deque<K> stack = new LinkedList<K>();
    private final Provider<Map<K, Object>> provider;
    private final Map<K,Object> buildee;
    public final MapTreeBuilder<K> ___;

    public MapTreeBuilder(Provider<Map<K, Object>> provider) {
        dbc.precondition(provider != null, "cannot create a MapTreeBuilder with a null map provider");
        this.provider = provider;
        this.buildee = provider.provide();
        this.___ = this;
    }

    public MapTreeBuilder<K> add(Map<K, ? extends Object> map) {
        dbc.precondition(map != null, "cannot merge a null map");
        current().putAll(map);
        return this;
    }

    public MapTreeBuilder<K> add(K key, Object value) {
        current().put(key, value);
        return this;
    }
    

    public MapTreeBuilder<K> push(K key) {
        current().put(key, provider.provide());
        stack.addLast(key);
        return this;
    }

    public MapTreeBuilder<K> pop() {
        dbc.precondition(stack.size() != 0, "popping from an empty stack");
        stack.removeLast();
        return this;
    }

    public Map<K, Object> toMap() {
        return buildee;
    }

    private Map<K, Object> current() {
        Map<K, Object> current = this.buildee;
        for (K nested : stack) {
            current = Casts.widen(current.get(nested));
        }
        return current;
    }
}
