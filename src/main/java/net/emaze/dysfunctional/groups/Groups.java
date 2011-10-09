package net.emaze.dysfunctional.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.dispatching.Dispatching;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public abstract class Groups {

    public static <K, V> Map<K, List<V>> groupBy(Iterator<V> groupies, Delegate<K, V> grouper) {
        final HashMapFactory<K, List<V>> hashMapFactory = new HashMapFactory<K, List<V>>();
        final Provider<List<V>> provider = Dispatching.compose(new Narrow<List<V>, ArrayList<V>>(), new ArrayListFactory<V>());
        return new GroupBy(grouper, provider, hashMapFactory).perform(groupies);
    }

    public static <C extends Collection<V>, K, V> Map<K, C> groupBy(Iterator<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider) {
        return new GroupBy(grouper, collectionProvider, new HashMapFactory<K, C>()).perform(groupies);
    }

    public static <M extends Map<K, C>, C extends Collection<V>, K, V> Map<K, C> groupBy(Iterator<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        return new GroupBy(grouper, collectionProvider, mapProvider).perform(groupies);
    }

    public static <K, V> Map<K, List<V>> groupBy(Iterable<V> groupies, Delegate<K, V> grouper) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return groupBy(groupies.iterator(), grouper);
    }

    public static <C extends Collection<V>, K, V> Map<K, C> groupBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return groupBy(groupies.iterator(), grouper, collectionProvider);
    }

    public static <M extends Map<K, C>, C extends Collection<V>, K, V> Map<K, C> groupBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return groupBy(groupies.iterator(), grouper, collectionProvider, mapProvider);
    }

    public static <T> Pair<List<T>, List<T>> partition(Iterator<T> values, Predicate<T> partitioner) {
        final Provider<List<T>> provider = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return new PartitionBy(partitioner, provider, provider).perform(values);
    }

    public static <C extends Collection<T>, T> Pair<C, C> partition(Iterator<T> values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        return new PartitionBy(partitioner, collectionsProvider, collectionsProvider).perform(values);
    }

    public static <CA extends Collection<T>, CR extends Collection<T>, T> Pair<CA, CR> partition(Iterator<T> values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        return new PartitionBy(partitioner, acceptedCollectionProvider, refusedCollectionProvider).perform(values);
    }

    public static <T> Pair<List<T>, List<T>> partition(Iterable<T> values, Predicate<T> partitioner) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return partition(values.iterator(), partitioner);
    }

    public static <C extends Collection<T>, T> Pair<C, C> partition(Iterable<T> values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return partition(values.iterator(), partitioner, collectionsProvider);
    }

    public static <CA extends Collection<T>, CR extends Collection<T>, T> Pair<CA, CR> partition(Iterable<T> values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return partition(values.iterator(), partitioner, acceptedCollectionProvider, refusedCollectionProvider);
    }

    public static <M extends Map<K, V>, K, V> Map<K, V> indexBy(V[] groupies, Delegate<K, V> grouper, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot index from a null array");
        final Iterator<V> iterator = Iterations.iterator(groupies);
        return indexBy(iterator, grouper, mapProvider);
    }

    public static <K, V> Map<K, V> indexBy(V[] groupies, Delegate<K, V> grouper) {
        dbc.precondition(groupies != null, "cannot index from a null array");
        final Iterator<V> iterator = Iterations.iterator(groupies);
        return indexBy(iterator, grouper, new HashMapFactory<K, V>());
    }

    public static <M extends Map<K, V>, K, V> Map<K, V> indexBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot index from a null iterable");
        return indexBy(groupies.iterator(), grouper, mapProvider);
    }

    public static <K, V> Map<K, V> indexBy(Iterable<V> groupies, Delegate<K, V> grouper) {
        dbc.precondition(groupies != null, "cannot index from a null iterable");
        return indexBy(groupies.iterator(), grouper, new HashMapFactory<K, V>());
    }

    public static <K, V> Map<K, V> indexBy(Iterator<V> groupies, Delegate<K, V> grouper) {
        return new IndexBy<HashMap<K, V>, K, V>(grouper, new HashMapFactory<K, V>()).perform(groupies);        
    }

    public static <M extends Map<K, V>, K, V> Map<K, V> indexBy(Iterator<V> groupies, Delegate<K, V> grouper, Provider<M> mapProvider) {
        return new IndexBy<M, K, V>(grouper, mapProvider).perform(groupies);
    }
}
