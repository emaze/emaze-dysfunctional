package net.emaze.dysfunctional;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.convolutions.ZipLongestIterator;
import net.emaze.dysfunctional.convolutions.ZipShortestIterator;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.iterations.OneTimeIterable;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * @author rferranti
 */
public abstract class Zips {

    /**
     * i.e: Zips.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
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
     * i.e: Zips.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
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
     * i.e: Zips.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
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
     * i.e: Zip.longest([1,2],['a','b','c']) -> [just(1),just('a')],
     * [just(2),just('b')], [nothing(),just('c')],
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
     * i.e: Zip.longest([1,2],['a','b','c']) -> [just(1),just('a')],
     * [just(2),just('b')], [nothing(),just('c')],
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
     * i.e: Zip.longest([1,2],['a','b','c']) -> [just(1),just('a')],
     * [just(2),just('b')], [nothing(),just('c')],
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
