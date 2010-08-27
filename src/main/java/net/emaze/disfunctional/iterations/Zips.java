package net.emaze.disfunctional.iterations;

import java.util.Iterator;
import net.emaze.disfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class Zips {

    public static <T1, T2> Iterable<Pair<T1, T2>> shortest(Iterable<T1> former, Iterable<T2> latter) {
        Iterator<Pair<T1,T2>> iterator = new ZipShortestIterator(former.iterator(), latter.iterator());
        return new OneTimeIterable( iterator );
    }

    public static <T1, T2> Iterable<Pair<T1, T2>> longest(Iterable<T1> former, Iterable<T2> latter) {
        Iterator<Pair<T1,T2>> iterator = new ZipLongestIterator(former.iterator(), latter.iterator());
        return new OneTimeIterable( iterator );
    }

}
