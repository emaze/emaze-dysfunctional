package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.collections.LinkedHashMapFactory;
import net.emaze.dysfunctional.collections.TreeMapFactory;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.NestedMapBuilder;
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

    public static <K, V> MapBuilder<K, V> sorted() {
        return new MapBuilder<K, V>(new LinkedHashMap<K, V>());
    }

    public static <K, V> MapBuilder<K, V> ordered() {
        return new MapBuilder<K, V>(new TreeMap<K, V>());
    }

    public static <K, V> MapBuilder<K, V> ordered(Comparator<K> keyComp) {
        return new MapBuilder<K, V>(new TreeMap<K, V>(keyComp));
    }

    public abstract static class Nested {

        public static <K> NestedMapBuilder<K> from(Provider<Map<K, Object>> provider) {
            return new NestedMapBuilder<K>(provider);
        }

        public static <K> NestedMapBuilder<K> builder() {
            return new NestedMapBuilder<K>(narrowed(new HashMapFactory<K, Object>()));
        }

        public static <K> NestedMapBuilder<K> sorted() {
            return new NestedMapBuilder<K>(narrowed(new LinkedHashMapFactory<K, Object>()));
        }

        public static <K extends Comparable<K>> NestedMapBuilder<K> ordered() {
            return new NestedMapBuilder<K>(narrowed(new TreeMapFactory<K, Object>(new ComparableComparator<K>())));
        }

        public static <K> NestedMapBuilder<K> ordered(Comparator<K> keyComp) {
            return new NestedMapBuilder<K>(narrowed(new TreeMapFactory<K, Object>(keyComp)));
        }

        private static <M extends Map<K, Object>, K> Provider<Map<K, Object>> narrowed(Provider<M> provider) {
            return Compositions.compose(new Narrow<Map<K, Object>, M>(), provider);
        }
    }
}
