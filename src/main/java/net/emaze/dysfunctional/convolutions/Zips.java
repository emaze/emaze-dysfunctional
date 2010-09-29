package net.emaze.dysfunctional.convolutions;

import java.util.Iterator;
import net.emaze.dysfunctional.iterations.OneTimeIterable;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * @author rferranti
 */
public abstract class Zips {

    /**
     * i.e: Zip.shortest([1,2],['a','b','c']) -> [1,'a'], [2,'b']
     * @param <T1>
     * @param <T2> 
     * @param former the former iterator
     * @param latter the latter iterator
     * @return the resulting shorted convolved iterator wrapped in a OneTimeIterable
     */
    public static <T1, T2> Iterable<Pair<T1, T2>> shortest(Iterable<T1> former, Iterable<T2> latter) {
        Iterator<Pair<T1, T2>> iterator = new ZipShortestIterator<T1, T2>(former.iterator(), latter.iterator());
        return new OneTimeIterable<Pair<T1, T2>>(iterator);
    }

    /**
     * i.e: Zip.shortest([1,2],['a','b','c']) -> [just(1),just('a')], [just(2),just('b')], [nothing(),just('c')],
     * @param <T1>
     * @param <T2>
     * @param former the former iterator
     * @param latter the latter iterator
     * @return the resulting longest convolved iterator wrapped in a OneTimeIterable
     */
    public static <T1, T2> Iterable<Pair<Maybe<T1>, Maybe<T2>>> longest(Iterable<T1> former, Iterable<T2> latter) {
        Iterator<Pair<Maybe<T1>, Maybe<T2>>> iterator = new ZipLongestIterator<T1, T2>(former.iterator(), latter.iterator());
        return new OneTimeIterable<Pair<Maybe<T1>, Maybe<T2>>>(iterator);
    }
}
