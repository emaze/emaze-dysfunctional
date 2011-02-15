package net.emaze.dysfunctional.pagination;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.adapting.ArrayIterator;
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
     * Creates a page view of an iterable.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterable the iterable to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Integer, List<T>> page(long start, long howMany, Iterable<T> iterable) {
        return Pagination.page(start, howMany, iterable.iterator());
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
     * Creates a page view of an iterator.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param iterator the iterator to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Long, List<T>> pagel(long start, long howMany, Iterator<T> iterator) {
        final List<T> sliced = Consumers.all(Filtering.slice(start, howMany, iterator));
        final long prefetchingCompensation = sliced.size() == howMany ? 1 : 0;
        final long fullSize = Reductions.reduce(iterator, new Count<T>(), start + sliced.size() + prefetchingCompensation);
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
        return Pagination.pagel(start, howMany, iterable.iterator());
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
     * Creates a page view of a collection.
     * @param <T> the element type parameter
     * @param start the index where the page starts
     * @param howMany the page size
     * @param collection the array to be sliced
     * @return a pair containing the iterator size and the requested page
     */
    public static <T> Pair<Long, List<T>> pagel(long start, long howMany, Collection<T> collection) {
        return Pair.of((long) collection.size(), Consumers.all(Filtering.slice(start, howMany, collection)));
    }
}
