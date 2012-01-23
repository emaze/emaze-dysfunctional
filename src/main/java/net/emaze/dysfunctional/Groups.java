package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.groups.GroupBy;
import net.emaze.dysfunctional.groups.IndexBy;
import net.emaze.dysfunctional.groups.PartitionBy;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public abstract class Groups {

    /**
     * Groups elements from an iterator in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @return a map containing grouped values
     */
    public static <K, V> Map<K, List<V>> groupBy(Iterator<V> groupies, Delegate<K, V> grouper) {
        final Provider<List<V>> provider = Dispatching.compose(new Narrow<List<V>, ArrayList<V>>(), new ArrayListFactory<V>());
        return new GroupBy<HashMap<K, List<V>>, List<V>, K, V>(grouper, provider, new HashMapFactory<K, List<V>>()).perform(groupies);
    }

    /**
     * Groups elements from an iterator in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <C> the collection type used as value of the map
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @return a map containing grouped values
     */
    public static <C extends Collection<V>, K, V> Map<K, C> groupBy(Iterator<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider) {
        return new GroupBy<HashMap<K, C>, C, K, V>(grouper, collectionProvider, new HashMapFactory<K, C>()).perform(groupies);
    }

    /**
     * Groups elements from an iterator in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <M> the result map type
     * @param <C> the collection type used as value of the map
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @param mapProvider the map collection provider
     * @return a map containing grouped values
     */
    public static <M extends Map<K, C>, C extends Collection<V>, K, V> Map<K, C> groupBy(Iterator<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        return new GroupBy<M, C, K, V>(grouper, collectionProvider, mapProvider).perform(groupies);
    }

    /**
     * Groups elements from an iterable in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @return a map containing grouped values
     */
    public static <K, V> Map<K, List<V>> groupBy(Iterable<V> groupies, Delegate<K, V> grouper) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        final Provider<List<V>> provider = Dispatching.compose(new Narrow<List<V>, ArrayList<V>>(), new ArrayListFactory<V>());
        return new GroupBy<HashMap<K, List<V>>, List<V>, K, V>(grouper, provider, new HashMapFactory<K, List<V>>()).perform(groupies.iterator());
    }

    /**
     * Groups elements from an iterable in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <C> the collection type used as value of the map
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @return a map containing grouped values
     */
    public static <C extends Collection<V>, K, V> Map<K, C> groupBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return new GroupBy<HashMap<K, C>, C, K, V>(grouper, collectionProvider, new HashMapFactory<K, C>()).perform(groupies.iterator());
    }

    /**
     * Groups elements from an iterable in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <M> the result map type
     * @param <C> the collection type used as value of the map
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @param mapProvider the map collection provider
     * @return a map containing grouped values
     */
    public static <M extends Map<K, C>, C extends Collection<V>, K, V> Map<K, C> groupBy(Iterable<V> groupies, Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return new GroupBy<M, C, K, V>(grouper, collectionProvider, mapProvider).perform(groupies.iterator());
    }

    /**
     * Groups elements from an array in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @return a map containing grouped values
     */
    public static <K, V> Map<K, List<V>> groupBy(V[] groupies, Delegate<K, V> grouper) {
        final Provider<List<V>> provider = Dispatching.compose(new Narrow<List<V>, ArrayList<V>>(), new ArrayListFactory<V>());
        return new GroupBy<HashMap<K, List<V>>, List<V>, K, V>(grouper, provider, new HashMapFactory<K, List<V>>()).perform(new ArrayIterator<V>(groupies));
    }

    /**
     * Groups elements from an array in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <C> the collection type used as value of the map
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @return a map containing grouped values
     */
    public static <C extends Collection<V>, K, V> Map<K, C> groupBy(V[] groupies, Delegate<K, V> grouper, Provider<C> collectionProvider) {
        return new GroupBy<HashMap<K, C>, C, K, V>(grouper, collectionProvider, new HashMapFactory<K, C>()).perform(new ArrayIterator<V>(groupies));
    }

    /**
     * Groups elements from an array in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <M> the result map type
     * @param <C> the collection type used as value of the map
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @param mapProvider the map collection provider
     * @return a map containing grouped values
     */
    public static <M extends Map<K, C>, C extends Collection<V>, K, V> Map<K, C> groupBy(V[] groupies, Delegate<K, V> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        return new GroupBy<M, C, K, V>(grouper, collectionProvider, mapProvider).perform(new ArrayIterator<V>(groupies));
    }

    /**
     * Partitions elements from the iterator into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @return the two partitioned collections in a pair.
     */
    public static <T> Pair<List<T>, List<T>> partition(Iterator<T> values, Predicate<T> partitioner) {
        final Provider<List<T>> provider = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return new PartitionBy<List<T>, List<T>, T>(partitioner, provider, provider).perform(values);
    }

    /**
     * Partitions elements from the iterator into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <C> the partitioned elements collection type
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param collectionsProvider a collection provider used to create both
     * elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <C extends Collection<T>, T> Pair<C, C> partition(Iterator<T> values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        return new PartitionBy<C, C, T>(partitioner, collectionsProvider, collectionsProvider).perform(values);
    }

    /**
     * Partitions elements from the iterator into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <CA> the accepted elements collection type
     * @param <CR> the refused elements collection type
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param acceptedCollectionProvider a collection provider used to create
     * accepted elements collection
     * @param refusedCollectionProvider a collection provider used to create
     * refused elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <CA extends Collection<T>, CR extends Collection<T>, T> Pair<CA, CR> partition(Iterator<T> values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        return new PartitionBy<CA, CR, T>(partitioner, acceptedCollectionProvider, refusedCollectionProvider).perform(values);
    }

    /**
     * Partitions elements from the iterable into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @return the two partitioned collections in a pair.
     */
    public static <T> Pair<List<T>, List<T>> partition(Iterable<T> values, Predicate<T> partitioner) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        final Provider<List<T>> provider = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return new PartitionBy<List<T>, List<T>, T>(partitioner, provider, provider).perform(values.iterator());
    }

    /**
     * Partitions elements from the iterable into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <C> the partitioned elements collection type
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param collectionsProvider a collection provider used to create both
     * elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <C extends Collection<T>, T> Pair<C, C> partition(Iterable<T> values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return new PartitionBy<C, C, T>(partitioner, collectionsProvider, collectionsProvider).perform(values.iterator());
    }

    /**
     * Partitions elements from the iterable into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <CA> the accepted elements collection type
     * @param <CR> the refused elements collection type
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param acceptedCollectionProvider a collection provider used to create
     * accepted elements collection
     * @param refusedCollectionProvider a collection provider used to create
     * refused elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <CA extends Collection<T>, CR extends Collection<T>, T> Pair<CA, CR> partition(Iterable<T> values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return new PartitionBy<CA, CR, T>(partitioner, acceptedCollectionProvider, refusedCollectionProvider).perform(values.iterator());
    }

    /**
     * Partitions elements from the array into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @return the two partitioned collections in a pair.
     */
    public static <T> Pair<List<T>, List<T>> partition(T[] values, Predicate<T> partitioner) {
        final Provider<List<T>> provider = Dispatching.compose(new Narrow<List<T>, ArrayList<T>>(), new ArrayListFactory<T>());
        return new PartitionBy<List<T>, List<T>, T>(partitioner, provider, provider).perform(new ArrayIterator<T>(values));
    }

    /**
     * Partitions elements from the array into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <C> the partitioned elements collection type
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param collectionsProvider a collection provider used to create both
     * elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <C extends Collection<T>, T> Pair<C, C> partition(T[] values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        return new PartitionBy<C, C, T>(partitioner, collectionsProvider, collectionsProvider).perform(new ArrayIterator<T>(values));
    }

    /**
     * Partitions elements from the array into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <CA> the accepted elements collection type
     * @param <CR> the refused elements collection type
     * @param <T> the element type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param acceptedCollectionProvider a collection provider used to create
     * accepted elements collection
     * @param refusedCollectionProvider a collection provider used to create
     * refused elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <CA extends Collection<T>, CR extends Collection<T>, T> Pair<CA, CR> partition(T[] values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        return new PartitionBy<CA, CR, T>(partitioner, acceptedCollectionProvider, refusedCollectionProvider).perform(new ArrayIterator<T>(values));
    }

    /**
     * Indexes elements from the iterable using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <M> the map type
     * @param <K> the key type
     * @param <V> the value type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @param mapProvider a provider used to create the resulting map
     * @return indexed elements in a map
     */
    public static <M extends Map<K, V>, K, V> Map<K, V> indexBy(Iterable<V> groupies, Delegate<K, V> indexer, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot index from a null iterable");
        return new IndexBy<M, K, V>(indexer, mapProvider).perform(groupies.iterator());
    }

    /**
     * Indexes elements from the iterable using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @return indexed elements in a map
     */
    public static <K, V> Map<K, V> indexBy(Iterable<V> groupies, Delegate<K, V> indexer) {
        dbc.precondition(groupies != null, "cannot index from a null iterable");
        return new IndexBy<HashMap<K, V>, K, V>(indexer, new HashMapFactory<K, V>()).perform(groupies.iterator());
    }

    /**
     * Indexes elements from the iterator using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @return indexed elements in a map
     */
    public static <M extends Map<K, V>, K, V> Map<K, V> indexBy(Iterator<V> groupies, Delegate<K, V> indexer, Provider<M> mapProvider) {
        return new IndexBy<M, K, V>(indexer, mapProvider).perform(groupies);
    }

    /**
     * Indexes elements from the iterator using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <M> the map type
     * @param <K> the key type
     * @param <V> the value type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @param mapProvider a provider used to create the resulting map
     * @return indexed elements in a map
     */
    public static <K, V> Map<K, V> indexBy(Iterator<V> groupies, Delegate<K, V> indexer) {
        return new IndexBy<HashMap<K, V>, K, V>(indexer, new HashMapFactory<K, V>()).perform(groupies);
    }

    /**
     * Indexes elements from the array using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <M> the map type
     * @param <K> the key type
     * @param <V> the value type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @param mapProvider a provider used to create the resulting map
     * @return indexed elements in a map
     */
    public static <M extends Map<K, V>, K, V> Map<K, V> indexBy(V[] groupies, Delegate<K, V> indexer, Provider<M> mapProvider) {
        return new IndexBy<M, K, V>(indexer, mapProvider).perform(new ArrayIterator<V>(groupies));
    }

    /**
     * Indexes elements from the array using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @return indexed elements in a map
     */
    public static <K, V> Map<K, V> indexBy(V[] groupies, Delegate<K, V> indexer) {
        return new IndexBy<HashMap<K, V>, K, V>(indexer, new HashMapFactory<K, V>()).perform(new ArrayIterator<V>(groupies));
    }
}
