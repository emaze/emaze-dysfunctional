package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.convolutions.ZipLongestIterator;
import net.emaze.dysfunctional.convolutions.ZipShortestIterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.DenseRange;
import net.emaze.dysfunctional.ranges.Endpoints;
import net.emaze.dysfunctional.ranges.Range;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * longest, shortest, counted.
 *
 * @author rferranti
 */
public abstract class Zips {

    /**
     * Transforms two iterables to an iterator of tuples, containing elements of
     * both iterables occurring at the same position; the iterator stops when
     * the shortest input iterable is exhausted. E.g:
     * <code>
     * Zips.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
     * </code>
     *
     * @param <T1> former element type parameter
     * @param <T2> latter element type parameter
     * @param former the former iterable
     * @param latter the latter iterable
     * @return the resulting shortest convolved iterator
     */
    public static <T1, T2> Iterator<Pair<T1, T2>> shortest(Iterable<T1> former, Iterable<T2> latter) {
        dbc.precondition(former != null, "cannot call shortest with a null former iterable");
        dbc.precondition(latter != null, "cannot call shortest with a null latter iterable");
        return new ZipShortestIterator<T1, T2>(former.iterator(), latter.iterator());
    }

    /**
     * Transforms two iterators to an iterator of tuples, containing elements of
     * both iterators occurring at the same position; the iterator stops when
     * the shortest input iterable is exhausted. E.g:
     * <code>
     * Zips.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
     * </code>
     *
     * @param <T1> former element type parameter
     * @param <T2> latter element type parameter
     * @param former the former iterator
     * @param latter the latter iterator
     * @return the resulting shortest convolved iterator
     */
    public static <T1, T2> Iterator<Pair<T1, T2>> shortest(Iterator<T1> former, Iterator<T2> latter) {
        return new ZipShortestIterator<T1, T2>(former, latter);
    }

    /**
     * Transforms two arrays to an iterator of tuples, containing elements of
     * both arrays occurring at the same position; the iterator stops when the
     * shortest input iterable is exhausted. E.g:
     * <code>
     * Zips.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
     * </code>
     *
     * @param <T1> former element type parameter
     * @param <T2> latter element type parameter
     * @param former the former array
     * @param latter the latter array
     * @return the resulting shortest convolved iterator
     */
    public static <T1, T2> Iterator<Pair<T1, T2>> shortest(T1[] former, T2[] latter) {
        return new ZipShortestIterator<T1, T2>(new ArrayIterator<T1>(former), new ArrayIterator<T2>(latter));
    }

    /**
     * Transforms two iterables to an iterator of tuples, containing
     * Maybe.just(elements) of both iterables occurring at the same position. If
     * the iterables are of uneven length, missing values are filled-in with
     * Maybe.nothing(). Iteration continues until the longest iterable is
     * exhausted. E.g:
     * <code>
     * Zip.longest([1,2],['a','b','c']) -> [(just 1,just 'a'),(just 2 ,just 'b'), (nothing ,just 'c')]
     * </code>
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param former the former iterable
     * @param latter the latter iterable
     * @return the resulting longest convolved iterator
     */
    public static <T1, T2> Iterator<Pair<Maybe<T1>, Maybe<T2>>> longest(Iterable<T1> former, Iterable<T2> latter) {
        dbc.precondition(former != null, "cannot call longest with a null former iterable");
        dbc.precondition(latter != null, "cannot call longest with a null latter iterable");
        return new ZipLongestIterator<T1, T2>(former.iterator(), latter.iterator());
    }

    /**
     * Transforms two iterators to an iterator of tuples, containing
     * Maybe.just(elements) of both iterators occurring at the same position. If
     * the iterables are of uneven length, missing values are filled-in with
     * Maybe.nothing(). Iteration continues until the longest iterable is
     * exhausted. E.g:
     * <code>
     * Zip.longest([1,2],['a','b','c']) -> [(just 1,just 'a'),(just 2 ,just 'b'), (nothing ,just 'c')]
     * </code>
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param former the former iterator
     * @param latter the latter iterator
     * @return the resulting longest convolved iterator
     */
    public static <T1, T2> Iterator<Pair<Maybe<T1>, Maybe<T2>>> longest(Iterator<T1> former, Iterator<T2> latter) {
        return new ZipLongestIterator<T1, T2>(former, latter);
    }

    /**
     * Transforms two arrays to an iterator of tuples, containing
     * Maybe.just(elements) of both arrays occurring at the same position. If
     * the iterables are of uneven length, missing values are filled-in with
     * Maybe.nothing(). Iteration continues until the longest iterable is
     * exhausted. E.g:
     * <code>
     * Zip.longest([1,2],['a','b','c']) -> [(just 1,just 'a'),(just 2 ,just 'b'), (nothing ,just 'c')]
     * </code>
     *
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param former the former array
     * @param latter the latter array
     * @return the resulting longest convolved iterator
     */
    public static <T1, T2> Iterator<Pair<Maybe<T1>, Maybe<T2>>> longest(T1[] former, T2[] latter) {
        return new ZipLongestIterator<T1, T2>(new ArrayIterator<T1>(former), new ArrayIterator<T2>(latter));
    }

    /**
     * Creates an iterator yielding values from the source iterator and its
     * index.
     *
     * <code>E.g:
     * counted(["a", "b","c"], [0..inf]) -> [(0,"a"), (1, "b"), (2, "c")]
     * </code>
     *
     *
     * @param <CT> the counter type
     * @param <ET> the element type
     * @param iterator the source iterator
     * @param range the source range
     * @return the resulting iterator
     */
    public static <CT, ET> Iterator<Pair<CT, ET>> counted(Iterator<ET> iterator, Range<CT> range) {
        return new ZipShortestIterator<CT, ET>(range.iterator(), iterator);
    }

    /**
     * Creates an iterator yielding values from the source iterator and its
     * index.
     *
     * <code>E.g:
     * counted(["a", "b","c"]) -> [(0,"a"), (1, "b"), (2, "c")]
     * </code>
     *
     * @param <T> the element type
     * @param iterator the source iterator
     * @return the resulting iterator
     */
    public static <T> Iterator<Pair<Integer, T>> counted(Iterator<T> iterator) {
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();
        final Range<Integer> range = new DenseRange<Integer>(sequencer, comparator, Endpoints.IncludeBoth, 0, Integer.MAX_VALUE);
        return new ZipShortestIterator<Integer, T>(range.iterator(), iterator);
    }

    /**
     * Creates an iterator yielding values from the source iterator and its
     * index.
     *
     * <code>E.g:
     * counted(["a", "b","c"]) -> [(0,"a"), (1, "b"), (2, "c")]
     * </code>
     *
     * @param <T> the element type
     * @param iterable the source iterable
     * @return the resulting iterator
     */
    public static <T> Iterator<Pair<Integer, T>> counted(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot call counted with a null iterable");
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();
        final Range<Integer> range = new DenseRange<Integer>(sequencer, comparator, Endpoints.IncludeBoth, 0, Integer.MAX_VALUE);
        return new ZipShortestIterator<Integer, T>(range.iterator(), iterable.iterator());
    }

    /**
     * Creates an iterator yielding values from the source iterator and its
     * index.
     *
     * <code>E.g:
     * counted(["a", "b","c"], [0..inf]) -> [(0,"a"), (1, "b"), (2, "c")]
     * </code>
     *
     * @param <CT> the counter type
     * @param <ET> the element type
     * @param iterable the source iterable
     * @param range the source range
     * @return the resulting iterator
     */
    public static <CT, ET> Iterator<Pair<CT, ET>> counted(Iterable<ET> iterable, Range<CT> range) {
        dbc.precondition(iterable != null, "cannot call counted with a null iterable");
        return new ZipShortestIterator<CT, ET>(range.iterator(), iterable.iterator());
    }

    /**
     * Creates an iterator yielding values from the source iterator and its
     * index.
     *
     * <code>E.g:
     * counted(["a", "b","c"]) -> [(0,"a"), (1, "b"), (2, "c")]
     * </code>
     *
     * @param <T> the element type
     * @param array the source array
     * @return the resulting iterator
     */
    public static <T> Iterator<Pair<Integer, T>> counted(T... array) {
        dbc.precondition(array != null, "cannot call counted with a null array");
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();
        final Range<Integer> range = new DenseRange<Integer>(sequencer, comparator, Endpoints.IncludeBoth, 0, Integer.MAX_VALUE);
        return new ZipShortestIterator<Integer, T>(range.iterator(), new ArrayIterator<T>(array));
    }

    /**
     * Creates an iterator yielding values from the source iterator and its
     * index.
     * <code>E.g:
     * counted(["a", "b","c"], [0..inf]) -> [(0,"a"), (1, "b"), (2, "c")]
     * </code>
     *
     * @param <CT> the counter type
     * @param <ET> the element type
     * @param array the source array
     * @param range the source range
     * @return the resulting iterator
     */
    public static <CT, ET> Iterator<Pair<CT, ET>> counted(ET[] array, Range<CT> range) {
        dbc.precondition(array != null, "cannot call counted with a null array");
        return new ZipShortestIterator<CT, ET>(range.iterator(), new ArrayIterator<ET>(array));
    }
}
