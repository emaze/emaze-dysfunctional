package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.convolutions.ZipLongestIterator;
import net.emaze.dysfunctional.convolutions.ZipShortestIterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.tuples.Pair;

/**
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
}
