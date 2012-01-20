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
import net.emaze.dysfunctional.Dispatching;
import net.emaze.dysfunctional.collections.builders.MapBuilder;
import net.emaze.dysfunctional.collections.builders.MapTreeBuilder;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.order.ComparableComparator;

public class Maps {

    public static <K, V> MapBuilder<K, V> from(Map<K, V> map) {
        return new MapBuilder<K, V>(map);
    }

    public static <K, V> MapBuilder<K, V> from(Provider<Map<K, V>> provider) {
        return new MapBuilder<K, V>(provider.provide());
    }

    public static <K, V> MapBuilder<K, V> unsorted() {
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

    public static class Tree {

        public static <K> MapTreeBuilder<K> from(Provider<Map<K, Object>> provider) {
            return new MapTreeBuilder<K>(provider);
        }

        public static <K> MapTreeBuilder<K> unsorted() {
            return new MapTreeBuilder<K>(narrowed(new HashMapFactory<K, Object>()));
        }

        public static <K> MapTreeBuilder<K> sorted() {
            return new MapTreeBuilder<K>(narrowed(new LinkedHashMapFactory<K, Object>()));
        }

        public static <K extends Comparable<K>> MapTreeBuilder<K> ordered() {
            return new MapTreeBuilder<K>(narrowed(new TreeMapFactory<K, Object>(new ComparableComparator<K>())));
        }

        public static <K> MapTreeBuilder<K> ordered(Comparator<K> keyComp) {
            return new MapTreeBuilder<K>(narrowed(new TreeMapFactory<K, Object>(keyComp)));
        }

        private static <M extends Map<K, Object>, K> Provider<Map<K, Object>> narrowed(Provider<M> provider) {
            return Dispatching.compose(new Narrow<Map<K, Object>, M>(), provider);
        }
    }
}
