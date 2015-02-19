package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.groups.GroupBy;
import net.emaze.dysfunctional.groups.IndexBy;
import net.emaze.dysfunctional.groups.PartitionBy;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * groupBy, partition, indexBy.
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
    public static <K, V> Map<K, List<V>> groupBy(Iterator<V> groupies, Function<V, K> grouper) {
        final Provider<List<V>> provider = Compositions.compose(new Vary<ArrayList<V>, List<V>>(), new ArrayListFactory<V>());
        return new GroupBy<>(grouper, provider, new HashMapFactory<>()).apply(groupies);
    }

    /**
     * Groups elements from an iterator in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param <C> the collection type used as value of the map
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @return a map containing grouped values
     */
    public static <K, V, C extends Collection<V>> Map<K, C> groupBy(Iterator<V> groupies, Function<V, K> grouper, Provider<C> collectionProvider) {
        return new GroupBy<>(grouper, collectionProvider, new HashMapFactory<>()).apply(groupies);
    }

    /**
     * Groups elements from an iterator in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param <C> the collection type used as value of the map
     * @param <M> the result map type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @param mapProvider the map collection provider
     * @return a map containing grouped values
     */
    public static <K, V, C extends Collection<V>, M extends Map<K, C>> Map<K, C> groupBy(Iterator<V> groupies, Function<V, K> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        return new GroupBy<>(grouper, collectionProvider, mapProvider).apply(groupies);
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
    public static <K, V> Map<K, List<V>> groupBy(Iterable<V> groupies, Function<V, K> grouper) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        final Provider<List<V>> provider = Compositions.compose(new Vary<ArrayList<V>, List<V>>(), new ArrayListFactory<V>());
        return new GroupBy<>(grouper, provider, new HashMapFactory<>()).apply(groupies.iterator());
    }

    /**
     * Groups elements from an iterable in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param <C> the collection type used as value of the map
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @return a map containing grouped values
     */
    public static <K, V, C extends Collection<V>> Map<K, C> groupBy(Iterable<V> groupies, Function<V, K> grouper, Provider<C> collectionProvider) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return new GroupBy<>(grouper, collectionProvider, new HashMapFactory<>()).apply(groupies.iterator());
    }

    /**
     * Groups elements from an iterable in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param <C> the collection type used as value of the map
     * @param <M> the result map type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @param mapProvider the map collection provider
     * @return a map containing grouped values
     */
    public static <K, V, C extends Collection<V>, M extends Map<K, C>> Map<K, C> groupBy(Iterable<V> groupies, Function<V, K> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot group a null iterable");
        return new GroupBy<>(grouper, collectionProvider, mapProvider).apply(groupies.iterator());
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
    public static <K, V> Map<K, List<V>> groupBy(V[] groupies, Function<V, K> grouper) {
        final Provider<List<V>> provider = Compositions.compose(new Vary<ArrayList<V>, List<V>>(), new ArrayListFactory<V>());
        return new GroupBy<>(grouper, provider, new HashMapFactory<>()).apply(new ArrayIterator<>(groupies));
    }

    /**
     * Groups elements from an array in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param <C> the collection type used as value of the map
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @return a map containing grouped values
     */
    public static <K, V, C extends Collection<V>> Map<K, C> groupBy(V[] groupies, Function<V, K> grouper, Provider<C> collectionProvider) {
        return new GroupBy<>(grouper, collectionProvider, new HashMapFactory<>()).apply(new ArrayIterator<>(groupies));
    }

    /**
     * Groups elements from an array in the key evaluated from the passed
     * delegate. E.g:
     * <code>groupBy([1, 2, 3, 1], id) -> { 1: [1,1], 2: [2], 3: [3]}</code>
     *
     * @param <K> the grouped key type
     * @param <V> the grouped value type
     * @param <C> the collection type used as value of the map
     * @param <M> the result map type
     * @param groupies the elements to be grouped
     * @param grouper the delegate used to group elements
     * @param collectionProvider the values collection provider
     * @param mapProvider the map collection provider
     * @return a map containing grouped values
     */
    public static <K, V, C extends Collection<V>, M extends Map<K, C>> Map<K, C> groupBy(V[] groupies, Function<V, K> grouper, Provider<C> collectionProvider, Provider<M> mapProvider) {
        return new GroupBy<>(grouper, collectionProvider, mapProvider).apply(new ArrayIterator<>(groupies));
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
        final Provider<List<T>> provider = Compositions.compose(new Vary<ArrayList<T>, List<T>>(), new ArrayListFactory<T>());
        return new PartitionBy<>(partitioner, provider, provider).apply(values);
    }

    /**
     * Partitions elements from the iterator into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param <C> the partitioned elements collection type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param collectionsProvider a collection provider used to create both
     * elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <T, C extends Collection<T>> Pair<C, C> partition(Iterator<T> values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        return new PartitionBy<>(partitioner, collectionsProvider, collectionsProvider).apply(values);
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
    public static <T, CA extends Collection<T>, CR extends Collection<T>> Pair<CA, CR> partition(Iterator<T> values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        return new PartitionBy<>(partitioner, acceptedCollectionProvider, refusedCollectionProvider).apply(values);
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
        final Provider<List<T>> provider = Compositions.compose(new Vary<ArrayList<T>, List<T>>(), new ArrayListFactory<T>());
        return new PartitionBy<>(partitioner, provider, provider).apply(values.iterator());
    }

    /**
     * Partitions elements from the iterable into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param <C> the partitioned elements collection type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param collectionsProvider a collection provider used to create both
     * elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <T, C extends Collection<T>> Pair<C, C> partition(Iterable<T> values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return new PartitionBy<>(partitioner, collectionsProvider, collectionsProvider).apply(values.iterator());
    }

    /**
     * Partitions elements from the iterable into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param <CA> the accepted elements collection type
     * @param <CR> the refused elements collection type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param acceptedCollectionProvider a collection provider used to create
     * accepted elements collection
     * @param refusedCollectionProvider a collection provider used to create
     * refused elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <T, CA extends Collection<T>, CR extends Collection<T>> Pair<CA, CR> partition(Iterable<T> values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        dbc.precondition(values != null, "cannot partition a null iterable");
        return new PartitionBy<>(partitioner, acceptedCollectionProvider, refusedCollectionProvider).apply(values.iterator());
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
        final Provider<List<T>> provider = Compositions.compose(new Vary<ArrayList<T>, List<T>>(), new ArrayListFactory<T>());
        return new PartitionBy<>(partitioner, provider, provider).apply(new ArrayIterator<>(values));
    }

    /**
     * Partitions elements from the array into two collections containing
     * elements that match the predicate (left side collection) and elements
     * that don't (right side collection). E.g:
     * <code>partition([1,2,3,4], isOdd) -> ([1,3],[2,4])</code>
     *
     * @param <T> the element type
     * @param <C> the partitioned elements collection type
     * @param values the values to be partitioned
     * @param partitioner the predicate used to partition values
     * @param collectionsProvider a collection provider used to create both
     * elements collection
     * @return the two partitioned collections in a pair.
     */
    public static <T, C extends Collection<T>> Pair<C, C> partition(T[] values, Predicate<T> partitioner, Provider<C> collectionsProvider) {
        return new PartitionBy<>(partitioner, collectionsProvider, collectionsProvider).apply(new ArrayIterator<>(values));
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
    public static <T, CA extends Collection<T>, CR extends Collection<T>> Pair<CA, CR> partition(T[] values, Predicate<T> partitioner, Provider<CA> acceptedCollectionProvider, Provider<CR> refusedCollectionProvider) {
        return new PartitionBy<>(partitioner, acceptedCollectionProvider, refusedCollectionProvider).apply(new ArrayIterator<>(values));
    }

    /**
     * Indexes elements from the iterable using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param <M> the map type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @param mapProvider a provider used to create the resulting map
     * @return indexed elements in a map
     */
    public static <K, V, M extends Map<K, V>> Map<K, V> indexBy(Iterable<V> groupies, Function<V, K> indexer, Provider<M> mapProvider) {
        dbc.precondition(groupies != null, "cannot index from a null iterable");
        return new IndexBy<>(indexer, mapProvider).apply(groupies.iterator());
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
    public static <K, V> Map<K, V> indexBy(Iterable<V> groupies, Function<V, K> indexer) {
        dbc.precondition(groupies != null, "cannot index from a null iterable");
        return new IndexBy<>(indexer, new HashMapFactory<>()).apply(groupies.iterator());
    }

    /**
     * Indexes elements from the iterator using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param <M> the map type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @param mapProvider a provider used to create the resulting map
     * @return indexed elements in a map
     */
    public static <K, V, M extends Map<K, V>> Map<K, V> indexBy(Iterator<V> groupies, Function<V, K> indexer, Provider<M> mapProvider) {
        return new IndexBy<>(indexer, mapProvider).apply(groupies);
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
    public static <K, V> Map<K, V> indexBy(Iterator<V> groupies, Function<V, K> indexer) {
        return new IndexBy<>(indexer, new HashMapFactory<>()).apply(groupies);
    }

    /**
     * Indexes elements from the array using passed delegate. E.g:
     * <code> indexBy([1,2,3,4], id) -> {1:1, 2:2, 3:3, 4:4}</code>
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param <M> the map type
     * @param groupies elements to be indexed
     * @param indexer the delegate used to index elements
     * @param mapProvider a provider used to create the resulting map
     * @return indexed elements in a map
     */
    public static <K, V, M extends Map<K, V>> Map<K, V> indexBy(V[] groupies, Function<V, K> indexer, Provider<M> mapProvider) {
        return new IndexBy<>(indexer, mapProvider).apply(new ArrayIterator<>(groupies));
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
    public static <K, V> Map<K, V> indexBy(V[] groupies, Function<V, K> indexer) {
        return new IndexBy<>(indexer, new HashMapFactory<>()).apply(new ArrayIterator<>(groupies));
    }
}
