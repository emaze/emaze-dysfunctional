package net.emaze.dysfunctional.pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.reductions.Count;
import net.emaze.dysfunctional.reductions.Reductions;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public abstract class Pagination {

    /**
     * Creates a page view of an iterator.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterator the iterator to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Integer, List<T>> page(long start, long howMany, Iterator<T> iterator) {
        final Pair<Long, List<T>> page = Pagination.pagel(start, howMany, iterator);
        dbc.stateprecondition(page.first() <= Integer.MAX_VALUE, "iterator size overflows an integer");
        return Pair.of(page.first().intValue(), page.second());
    }

    /**
     * Creates a page view of an iterator adding elements to the collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterator the iterator to be sliced
     * @param collection the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Integer, C> page(long start, long howMany, Iterator<T> iterator, C collection) {
        final Pair<Long, C> page = Pagination.pagel(start, howMany, iterator, collection);
        dbc.stateprecondition(page.first() <= Integer.MAX_VALUE, "iterator size overflows an integer");
        return Pair.of(page.first().intValue(), page.second());
    }

    /**
     * Creates a page view of an iterable.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterable the iterable to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Integer, List<T>> page(long start, long howMany, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot call page with a null iterable");
        return Pagination.page(start, howMany, iterable.iterator());
    }

    /**
     * Creates a page view of an iterable adding elements to the collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterable the iterable to be sliced
     * @param collection the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Integer, C> page(long start, long howMany, Iterable<T> iterable, C collection) {
        dbc.precondition(iterable != null, "cannot call page with a null iterable");
        return Pagination.page(start, howMany, iterable.iterator(), collection);
    }

    /**
     * Creates a page view of an array.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param array the array to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Integer, List<T>> page(long start, long howMany, T[] array) {
        return Pagination.page(start, howMany, new ArrayIterator<T>(array));
    }

    /**
     * Creates a page view of an array adding elements to the collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param array the array to be sliced
     * @param collection the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Integer, C> page(long start, long howMany, T[] array, C collection) {
        return Pagination.page(start, howMany, new ArrayIterator<T>(array), collection);
    }

    /**
     * Creates a page view of a collection.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param collection the iterable to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Integer, List<T>> page(long start, long howMany, Collection<T> collection) {
        return Pair.of(collection.size(), Consumers.all(Filtering.slice(start, howMany, collection)));
    }

    /**
     * Creates a page view of a collection adding elements to the out collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param in the iterable to be sliced
     * @param out the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Integer, C> page(long start, long howMany, Collection<T> in, C out) {
        return Pair.of(in.size(), Consumers.all(Filtering.slice(start, howMany, in), out));
    }

    /**
     * Creates a page view of an iterator.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterator the iterator to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Long, List<T>> pagel(long start, long howMany, Iterator<T> iterator) {
        final List<T> collection = new ArrayList<T>();
        return Pagination.pagel(start, howMany, iterator, collection);
    }

    /**
     * Creates a page view of an iterator adding elements to the collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterator the iterator to be sliced
     * @param collection the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Long, C> pagel(long start, long howMany, Iterator<T> iterator, C collection) {
        final C sliced = Consumers.all(Filtering.slice(start, howMany, iterator), collection);
        final long fullSize = Reductions.reduce(iterator, new Count<T>(), start + sliced.size());
        return Pair.of(fullSize, sliced);
    }

    /**
     * Creates a page view of an iterable.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterable the iterable to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Long, List<T>> pagel(long start, long howMany, Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot call page with a null iterable");
        return Pagination.pagel(start, howMany, iterable.iterator());
    }

    /**
     * Creates a page view of an iterable adding elements to the collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterable the iterable to be sliced
     * @param collection the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Long, C> pagel(long start, long howMany, Iterable<T> iterable, C collection) {
        dbc.precondition(iterable != null, "cannot call pagel with a null iterable");
        return Pagination.pagel(start, howMany, iterable.iterator(), collection);
    }

    /**
     * Creates a page view of an array.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param array the array to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Long, List<T>> pagel(long start, long howMany, T[] array) {
        return Pagination.pagel(start, howMany, new ArrayIterator<T>(array));
    }

    /**
     * Creates a page view of an array adding elements to the collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param array the array to be sliced
     * @param collection the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Long, C> pagel(long start, long howMany, T[] array, C collection) {
        return Pagination.pagel(start, howMany, new ArrayIterator<T>(array), collection);
    }

    /**
     * Creates a page view of a collection.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param collection the array to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Long, List<T>> pagel(long start, long howMany, Collection<T> collection) {
        dbc.precondition(collection != null, "cannot call pagel with a null collection");
        return Pair.of((long) collection.size(), Consumers.all(Filtering.slice(start, howMany, collection)));
    }

    /**
     * Creates a page view of a collection adding elements to the out collection.
     * @param <T> the element type parameter
     * @param <C> the collection type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param in the array to be sliced
     * @param out the output collection
     * @return a pair containing the iterator size and the requested page
     */
    public static <T, C extends Collection<T>> Pair<Long, C> pagel(long start, long howMany, Collection<T> in, C out) {
        dbc.precondition(in != null, "cannot call pagel with a null input collection");
        return Pair.of((long) in.size(), Consumers.all(Filtering.slice(start, howMany, in), out));
    }
}
