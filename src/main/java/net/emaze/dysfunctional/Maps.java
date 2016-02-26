package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import net.emaze.dysfunctional.collections.TreeMapFactory;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.NestedMapBuilder;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.order.ComparableComparator;

public abstract class Maps {

    public static <K, V> MapBuilder<K, V> from(Map<K, V> map) {
        return new MapBuilder<K, V>(map);
    }

    public static <K, V> MapBuilder<K, V> from(Provider<Map<K, V>> provider) {
        return new MapBuilder<K, V>(provider.provide());
    }

    public static <K, V> MapBuilder<K, V> builder() {
        return new MapBuilder<K, V>(new HashMap<K, V>());
    }

    public static <K, V> MapBuilder<K, V> linked() {
        return new MapBuilder<K, V>(new LinkedHashMap<K, V>());
    }

    public static <K, V> MapBuilder<K, V> tree() {
        return new MapBuilder<K, V>(new TreeMap<K, V>());
    }

    public static <K, V> MapBuilder<K, V> tree(Comparator<K> keyComp) {
        return new MapBuilder<K, V>(new TreeMap<K, V>(keyComp));
    }

    public static <K, V, W> Map<W, V> mapKeys(Map<K, V> input, Delegate<W, K> keysMapper) {
        dbc.precondition(input != null, "input map cannot be null");
        dbc.precondition(keysMapper != null, "keysMapper cannot be null");
        final Map<W, V> result = Maps.<W, V>builder().toMap();
        for (Entry<K, V> entry : input.entrySet()) {
            result.put(keysMapper.perform(entry.getKey()), entry.getValue());
        }
        return result;
    }

    public static <K, V, W> Map<W, V> mapKeys(Map<K, V> input, Delegate<W, K> keysMapper, BinaryDelegate<V, V, V> valueMerger) {
        dbc.precondition(input != null, "input map cannot be null");
        dbc.precondition(keysMapper != null, "keysMapper cannot be null");
        dbc.precondition(valueMerger != null, "valueMerger cannot be null");
        final Map<W, V> result = Maps.<W, V>builder().toMap();
        for (Entry<K, V> entry : input.entrySet()) {
            final W newKey = keysMapper.perform(entry.getKey());
            V newValue = entry.getValue();
            if (result.containsKey(newKey)) {
                final V previousValue = result.get(newKey);
                newValue = valueMerger.perform(previousValue, newValue);
            }
            result.put(newKey, newValue);
        }
        return result;
    }

    public static <K, V, W> Map<K, W> mapValues(Map<K, V> input, Delegate<W, V> valuesMapper) {
        dbc.precondition(input != null, "input map cannot be null");
        dbc.precondition(valuesMapper != null, "valuesMapper cannot be null");
        final Map<K, W> result = Maps.<K, W>builder().toMap();
        for (Entry<K, V> entry : input.entrySet()) {
            result.put(entry.getKey(), valuesMapper.perform(entry.getValue()));
        }
        return result;
    }

    public static <K, V, KK, VV> Map<KK, VV> mapKeysAndValues(Map<K, V> input, Delegate<KK, K> keysMapper, Delegate<VV, V> valuesMapper) {
        dbc.precondition(input != null, "input map cannot be null");
        dbc.precondition(keysMapper != null, "keysMapper cannot be null");
        dbc.precondition(valuesMapper != null, "valuesMapper cannot be null");
        final Map<KK, VV> result = Maps.<KK, VV>builder().toMap();
        for (Entry<K, V> entry : input.entrySet()) {
            result.put(keysMapper.perform(entry.getKey()), valuesMapper.perform(entry.getValue()));
        }
        return result;
    }

    public static <K, V, KK, VV> Map<KK, VV> mapKeysAndValues(Map<K, V> input, Delegate<KK, K> keysMapper, Delegate<VV, V> valuesMapper, BinaryDelegate<VV, VV, VV> valueMerger) {
        dbc.precondition(input != null, "input map cannot be null");
        dbc.precondition(keysMapper != null, "keysMapper cannot be null");
        dbc.precondition(valuesMapper != null, "valuesMapper cannot be null");
        dbc.precondition(valueMerger != null, "valueMerger cannot be null");
        final Map<KK, VV> result = Maps.<KK, VV>builder().toMap();
        for (Entry<K, V> entry : input.entrySet()) {
            final KK newKey = keysMapper.perform(entry.getKey());
            VV newValue = valuesMapper.perform(entry.getValue());
            if (result.containsKey(newKey)) {
                final VV previousValue = result.get(newKey);
                newValue = valueMerger.perform(previousValue, newValue);
            }
            result.put(newKey, newValue);
        }
        return result;
    }

    public abstract static class Nested {

        public static <K> NestedMapBuilder<K> from(Provider<Map<K, Object>> provider) {
            return new NestedMapBuilder<K>(provider);
        }

        public static <K> NestedMapBuilder<K> builder() {
            return new NestedMapBuilder<K>(narrowed(new HashMapFactory<K, Object>()));
        }

        public static <K> NestedMapBuilder<K> linked() {
            return new NestedMapBuilder<K>(narrowed(new LinkedHashMapFactory<K, Object>()));
        }

        public static <K extends Comparable<K>> NestedMapBuilder<K> tree() {
            return new NestedMapBuilder<K>(narrowed(new TreeMapFactory<K, Object>(new ComparableComparator<K>())));
        }

        public static <K> NestedMapBuilder<K> tree(Comparator<K> keyComp) {
            return new NestedMapBuilder<K>(narrowed(new TreeMapFactory<K, Object>(keyComp)));
        }

        private static <M extends Map<K, Object>, K> Provider<Map<K, Object>> narrowed(Provider<M> provider) {
            return Compositions.compose(new Vary<Map<K, Object>, M>(), provider);
        }
    }
}
