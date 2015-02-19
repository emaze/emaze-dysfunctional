package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.collections.HashMapFactory;
import net.emaze.dysfunctional.consumers.ConsumeIntoCollection;
import net.emaze.dysfunctional.consumers.ConsumeIntoMap;
import net.emaze.dysfunctional.consumers.ConsumeIntoOutputIterator;
import net.emaze.dysfunctional.consumers.FirstElement;
import net.emaze.dysfunctional.consumers.LastElement;
import net.emaze.dysfunctional.consumers.MaybeFirstElement;
import net.emaze.dysfunctional.consumers.MaybeLastElement;
import net.emaze.dysfunctional.consumers.MaybeOneElement;
import net.emaze.dysfunctional.consumers.OneElement;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.filtering.AtIndex;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.filtering.Nth;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.output.OutputIterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * all, maybeFirst, first, maybeOne, one, maybeLast, last, nth, maybeNth, at,
 * maybeAt, pipe.
 *
 * @author rferranti
 */
public abstract class Consumers {

    /**
     * Yields all elements of the iterator (in the provided collection).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterator the iterator that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(Iterator<E> iterator, R collection) {
        dbc.precondition(collection != null, "cannot call all with a null collection");
        final Function<Iterator<E>, R> consumer = new ConsumeIntoCollection<>(new ConstantProvider<R>(collection));
        return consumer.apply(iterator);
    }

    /**
     * Yields all elements of the iterator (in the provided collection).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(Iterable<E> iterable, R collection) {
        dbc.precondition(iterable != null, "cannot call all with a null iterable");
        dbc.precondition(collection != null, "cannot call all with a null collection");
        final Function<Iterator<E>, R> consumer = new ConsumeIntoCollection<>(new ConstantProvider<R>(collection));
        return consumer.apply(iterable.iterator());
    }

    /**
     * Yields all elements of the array (in the provided collection).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param array the array that will be consumed
     * @param collection the collection where the iterator is consumed
     * @return the collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(E[] array, R collection) {
        dbc.precondition(collection != null, "cannot call all with a null collection");
        final Function<Iterator<E>, R> consumer = new ConsumeIntoCollection<>(new ConstantProvider<R>(collection));
        return consumer.apply(new ArrayIterator<>(array));
    }

    /**
     * Yields all elements of the iterator (in a collection created by the
     * provider).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterator the iterator that will be consumed
     * @param provider the factory used to get the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterator<E> iterator, Supplier<R> provider) {
        final Function<Iterator<E>, R> consumer = new ConsumeIntoCollection<>(provider);
        return consumer.apply(iterator);
    }

    /**
     * Yields all elements of the iterator (in a collection created by the
     * provider).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param iterable the iterable that will be consumed
     * @param provider the factory used to get the returned collection
     * @return a collection filled with iterator values
     */
    public static <E, R extends Collection<E>> R all(Iterable<E> iterable, Supplier<R> provider) {
        dbc.precondition(iterable != null, "cannot call all with a null iterable");
        final Function<Iterator<E>, R> consumer = new ConsumeIntoCollection<>(provider);
        return consumer.apply(iterable.iterator());
    }

    /**
     * Yields all elements of the iterator (in a collection created by the
     * provider).
     *
     * @param <R> the returned collection type
     * @param <E> the collection element type
     * @param array the array that will be consumed
     * @param provider the factory used to get the returned collection
     * @return a collection filled with iterator values
     */
    public static <R extends Collection<E>, E> R all(E[] array, Supplier<R> provider) {
        final Function<Iterator<E>, R> consumer = new ConsumeIntoCollection<>(provider);
        return consumer.apply(new ArrayIterator<>(array));
    }

    /**
     * yields all elements of the iterator (in a list).
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return a list filled with iterator values
     */
    public static <E> List<E> all(Iterator<E> iterator) {
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        return consumer.apply(iterator);
    }

    /**
     * Yields all elements of the iterable's iterator (in a list).
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @return a list filled with iterable values
     */
    public static <E> List<E> all(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call all with a null iterable");
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        return consumer.apply(iterable.iterator());
    }

    /**
     * Yields all element of the array in a list.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @return a list filled with array values
     */
    public static <E> List<E> all(E[] array) {
        final Function<Iterator<E>, ArrayList<E>> consumer = new ConsumeIntoCollection<>(new ArrayListFactory<E>());
        return consumer.apply(new ArrayIterator<>(array));
    }

    /**
     * Yields all elements of the iterator (in the provided map).
     *
     * @param <M> the returned map type
     * @param <K> the map key type
     * @param <V> the map value type
     * @param iterator the iterator that will be consumed
     * @param map the map where the iterator is consumed
     * @return the map filled with iterator values
     */
    public static <M extends Map<K, V>, K, V> M dict(Iterator<Pair<K, V>> iterator, M map) {
        dbc.precondition(map != null, "cannot call dict with a null map");
        final Function<Iterator<Pair<K, V>>, M> consumer = new ConsumeIntoMap<>(new ConstantProvider<M>(map));
        return consumer.apply(iterator);
    }

    /**
     * Yields all elements of the iterator (in the provided map).
     *
     * @param <M> the returned map type
     * @param <K> the map key type
     * @param <V> the map value type
     * @param iterable the iterable that will be consumed
     * @param map the map where the iterator is consumed
     * @return the map filled with iterator values
     */
    public static <M extends Map<K, V>, K, V> M dict(Iterable<Pair<K, V>> iterable, M map) {
        dbc.precondition(iterable != null, "cannot call dict with a null iterable");
        dbc.precondition(map != null, "cannot call dict with a null map");
        final Function<Iterator<Pair<K, V>>, M> consumer = new ConsumeIntoMap<>(new ConstantProvider<M>(map));
        return consumer.apply(iterable.iterator());
    }

    /**
     * Yields all elements of the array (in the provided map).
     *
     * @param <M> the returned map type
     * @param <K> the map key type
     * @param <V> the map value type
     * @param map the map where the iterator is consumed
     * @param array the array that will be consumed
     * @return the map filled with iterator values
     */
    public static <M extends Map<K, V>, K, V> M dict(M map, Pair<K, V>... array) {
        dbc.precondition(map != null, "cannot call dict with a null map");
        final Function<Iterator<Pair<K, V>>, M> consumer = new ConsumeIntoMap<>(new ConstantProvider<M>(map));
        return consumer.apply(new ArrayIterator<>(array));
    }

    /**
     * Yields all elements of the iterator (in a map created by the provider).
     *
     * @param <M> the returned map type
     * @param <K> the map key type
     * @param <V> the map value type
     * @param iterator the iterator that will be consumed
     * @param provider the factory used to get the returned map
     * @return a map filled with iterator values
     */
    public static <M extends Map<K, V>, K, V> M dict(Iterator<Pair<K, V>> iterator, Supplier<M> provider) {
        final Function<Iterator<Pair<K, V>>, M> consumer = new ConsumeIntoMap<>(provider);
        return consumer.apply(iterator);
    }

    /**
     * Yields all elements of the iterator (in a map created by the provider).
     *
     * @param <M> the returned map type
     * @param <K> the map key type
     * @param <V> the map value type
     * @param iterable the iterable that will be consumed
     * @param provider the factory used to get the returned map
     * @return a map filled with iterator values
     */
    public static <M extends Map<K, V>, K, V> M dict(Iterable<Pair<K, V>> iterable, Supplier<M> provider) {
        dbc.precondition(iterable != null, "cannot call dict with a null iterable");
        final Function<Iterator<Pair<K, V>>, M> consumer = new ConsumeIntoMap<>(provider);
        return consumer.apply(iterable.iterator());
    }

    /**
     * Yields all elements of the iterator (in a map created by the provider).
     *
     * @param <M> the returned map type
     * @param <K> the map key type
     * @param <V> the map value type
     * @param provider the factory used to get the returned map
     * @param array the array that will be consumed
     * @return a map filled with iterator values
     */
    public static <M extends Map<K, V>, K, V> M dict(Supplier<M> provider, Pair<K, V>... array) {
        final Function<Iterator<Pair<K, V>>, M> consumer = new ConsumeIntoMap<>(provider);
        return consumer.apply(new ArrayIterator<>(array));
    }

    /**
     * Yields all elements of the iterator (in a map).
     *
     * @param <K> the map key type
     * @param <V> the map value type
     * @param iterator the iterator that will be consumed
     * @return a list filled with iterator values
     */
    public static <K, V> Map<K, V> dict(Iterator<Pair<K, V>> iterator) {
        final Function<Iterator<Pair<K, V>>, HashMap<K, V>> consumer = new ConsumeIntoMap<>(new HashMapFactory<K, V>());
        return consumer.apply(iterator);
    }

    /**
     * Yields all elements of the iterable's iterator (in a map).
     *
     * @param <K> the map key type
     * @param <V> the map value type
     * @param iterable the iterable that will be consumed
     * @return a list filled with iterable values
     */
    public static <K, V> Map<K, V> dict(Iterable<Pair<K, V>> iterable) {
        dbc.precondition(iterable != null, "cannot call dict with a null iterable");
        final Function<Iterator<Pair<K, V>>, HashMap<K, V>> consumer = new ConsumeIntoMap<>(new HashMapFactory<K, V>());
        return consumer.apply(iterable.iterator());
    }

    /**
     * Yields all element of the array in a map.
     *
     * @param <K> the map key type
     * @param <V> the map value type
     * @param array the array that will be consumed
     * @return a map filled with array values
     */
    public static <K, V> Map<K, V> dict(Pair<K, V>... array) {
        final Function<Iterator<Pair<K, V>>, HashMap<K, V>> consumer = new ConsumeIntoMap<>(new HashMapFactory<K, V>());
        return consumer.apply(new ArrayIterator<>(array));
    }

    /**
     * Consumes the input iterator to the output iterator.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @param outputIterator the iterator that will be filled
     */
    public static <E> void pipe(Iterator<E> iterator, OutputIterator<E> outputIterator) {
        new ConsumeIntoOutputIterator<>(outputIterator).apply(iterator);
    }

    /**
     * Consumes an iterable into the output iterator.
     *
     * @param <E> the iterator element type
     * @param iterable the iterable that will be consumed
     * @param outputIterator the iterator that will be filled
     */
    public static <E> void pipe(Iterable<E> iterable, OutputIterator<E> outputIterator) {
        dbc.precondition(iterable != null, "cannot call pipe with a null iterable");
        new ConsumeIntoOutputIterator<>(outputIterator).apply(iterable.iterator());
    }

    /**
     * Consumes the array into the output iterator.
     *
     * @param <E> the iterator element type
     * @param array the array that will be consumed
     * @param outputIterator the iterator that will be filled
     */
    public static <E> void pipe(E[] array, OutputIterator<E> outputIterator) {
        new ConsumeIntoOutputIterator<>(outputIterator).apply(new ArrayIterator<>(array));
    }

    /**
     * Yields the first element if present, nothing otherwise.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeFirst with a null iterable");
        return new MaybeFirstElement<E>().apply(iterable.iterator());
    }

    /**
     * Yields the first element if present, nothing otherwise.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(Iterator<E> iterator) {
        return new MaybeFirstElement<E>().apply(iterator);
    }

    /**
     * Yields the first element if present, nothing otherwise.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @return just the first element or nothing
     */
    public static <E> Maybe<E> maybeFirst(E[] array) {
        return new MaybeFirstElement<E>().apply(new ArrayIterator<>(array));
    }

    /**
     * Yields the first element of the iterator.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @throws IllegalArgumentException if no element is present
     * @return the found element
     */
    public static <E> E first(Iterator<E> iterator) {
        return new FirstElement<E>().apply(iterator);
    }

    /**
     * Yields the first element of the iterable.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @throws IllegalArgumentException if no element is present
     * @return the found element
     */
    public static <E> E first(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call first with a null iterable");
        return new FirstElement<E>().apply(iterable.iterator());
    }

    /**
     * Yields the first element of the array.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E first(E[] array) {
        return new FirstElement<E>().apply(new ArrayIterator<>(array));
    }

    /**
     * Yields the only element if found, nothing otherwise.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeOne(Iterator<E> iterator) {
        return new MaybeOneElement<E>().apply(iterator);
    }

    /**
     * Yields the only element if found, nothing otherwise.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeOne(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeOne with a null iterable");
        return new MaybeOneElement<E>().apply(iterable.iterator());
    }

    /**
     * Yields the only element if found, nothing otherwise.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @throws IllegalStateException if the iterator contains more than one
     * element
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeOne(E[] array) {
        return new MaybeOneElement<E>().apply(new ArrayIterator<>(array));
    }

    /**
     * Yields the only element.
     *
     * @param <E> the element type parameter
     * @param iterator the iterator to be searched
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E one(Iterator<E> iterator) {
        return new OneElement<E>().apply(iterator);
    }

    /**
     * Yields the only element.
     *
     * @param <E> the element type parameter
     * @param iterable the iterable to be searched
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E one(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call one with a null iterable");
        return new OneElement<E>().apply(iterable.iterator());
    }

    /**
     * Yields the only element.
     *
     * @param <E> the element type parameter
     * @param array the array to be searched
     * @throws IllegalStateException if more than one element is found
     * @throws IllegalArgumentException if no element matches
     * @return the found element
     */
    public static <E> E one(E[] array) {
        return new OneElement<E>().apply(new ArrayIterator<>(array));
    }

    /**
     * Yields the last element if present, nothing otherwise.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed
     * @return the last element or nothing
     */
    public static <E> Maybe<E> maybeLast(Iterator<E> iterator) {
        return new MaybeLastElement<E>().apply(iterator);
    }

    /**
     * Yields the last element if present, nothing otherwise.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed
     * @return the last element or nothing
     */
    public static <E> Maybe<E> maybeLast(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeLast with a null iterable");
        return new MaybeLastElement<E>().apply(iterable.iterator());
    }

    /**
     * Yields the last element if present, nothing otherwise.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed
     * @return the last element or nothing
     */
    public static <E> Maybe<E> maybeLast(E[] array) {
        return new MaybeLastElement<E>().apply(new ArrayIterator<>(array));
    }

    /**
     * Yields the last element.
     *
     * @param <E> the iterator element type
     * @param iterator the iterator that will be consumed @throw
     * IllegalArgumentException if no element is found
     * @return the last element
     */
    public static <E> E last(Iterator<E> iterator) {
        return new LastElement<E>().apply(iterator);
    }

    /**
     * Yields the last element.
     *
     * @param <E> the iterable element type
     * @param iterable the iterable that will be consumed @throw
     * IllegalArgumentException if no element is found
     * @return the last element
     */
    public static <E> E last(Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call last with a null iterable");
        return new LastElement<E>().apply(iterable.iterator());
    }

    /**
     * Yields the last element.
     *
     * @param <E> the array element type
     * @param array the array that will be consumed @throw
     * IllegalArgumentException if no element is found
     * @return the last element
     */
    public static <E> E last(E[] array) {
        return new LastElement<E>().apply(new ArrayIterator<>(array));
    }

    /**
     * Yields nth (1-based) element of the iterator.
     *
     * @param <E> the iterator element type
     * @param count the element cardinality
     * @param iterator the iterator that will be consumed
     * @return the nth element
     */
    public static <E> E nth(long count, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new Nth<E>(count));
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Yields nth (1-based) element of the iterable.
     *
     * @param <E> the iterable element type
     * @param count the element cardinality
     * @param iterable the iterable that will be consumed
     * @return the nth element
     */
    public static <E> E nth(long count, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call nth with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new Nth<E>(count));
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Yields nth (1-based) element of the array.
     *
     * @param <E> the array element type
     * @param count the element cardinality
     * @param array the array that will be consumed
     * @return the nth element
     */
    public static <E> E nth(long count, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new Nth<E>(count));
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Yields nth (1-based) element of the iterator if found or nothing.
     *
     * @param <E> the iterator element type
     * @param count the element cardinality
     * @param iterator the iterator that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeNth(long count, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new Nth<E>(count));
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Yields nth (1-based) element of the iterable if found or nothing.
     *
     * @param <E> the iterable element type
     * @param count the element cardinality
     * @param iterable the iterable that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeNth(long count, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeNth with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new Nth<E>(count));
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Yields nth (1-based) element of the array if found or nothing.
     *
     * @param <E> the array element type
     * @param count the element cardinality
     * @param array the array that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeNth(long count, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new Nth<E>(count));
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterator.
     *
     * @param <E> the iterator element type
     * @param index the element index
     * @param iterator the iterator that will be consumed
     * @return the element
     */
    public static <E> E at(long index, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new AtIndex<E>(index));
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterable.
     *
     * @param <E> the iterable element type
     * @param index the element index
     * @param iterable the iterable that will be consumed
     * @return the element
     */
    public static <E> E at(long index, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call at with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new AtIndex<E>(index));
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Yields element at (0-based) position of the array.
     *
     * @param <E> the array element type
     * @param index the element index
     * @param array the array that will be consumed
     * @return just the element or nothing
     */
    public static <E> E at(long index, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new AtIndex<E>(index));
        return new FirstElement<E>().apply(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterator if found or nothing.
     *
     * @param <E> the iterator element type
     * @param index the element index
     * @param iterator the iterator that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeAt(long index, Iterator<E> iterator) {
        final Iterator<E> filtered = new FilteringIterator<E>(iterator, new AtIndex<E>(index));
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Yields element at (0-based) position of the iterable if found or nothing.
     *
     * @param <E> the iterable element type
     * @param index the element index
     * @param iterable the iterable that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeAt(long index, Iterable<E> iterable) {
        dbc.precondition(iterable != null, "cannot call maybeAt with a null iterable");
        final Iterator<E> filtered = new FilteringIterator<E>(iterable.iterator(), new AtIndex<E>(index));
        return new MaybeFirstElement<E>().apply(filtered);
    }

    /**
     * Yields element at (0-based) position of the array if found or nothing.
     *
     * @param <E> the array element type
     * @param index the element index
     * @param array the array that will be consumed
     * @return just the element or nothing
     */
    public static <E> Maybe<E> maybeAt(long index, E[] array) {
        final Iterator<E> filtered = new FilteringIterator<E>(new ArrayIterator<E>(array), new AtIndex<E>(index));
        return new MaybeFirstElement<E>().apply(filtered);
    }
}
