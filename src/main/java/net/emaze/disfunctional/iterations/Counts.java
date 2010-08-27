package net.emaze.disfunctional.iterations;

import java.util.Iterator;
import net.emaze.disfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class Counts {

    public static <T> Iterable<Pair<Integer,T>> counted(Iterable<T> iter) {
        return counted(iter,0,Integer.MAX_VALUE);
    }
    
    public static <T> Iterable<Pair<Integer,T>> counted(Iterable<T> iter, int from , int upTo) {
        final Iterator<Pair<Integer,T>> iterator = new CountedIterator(iter.iterator(), 0, Integer.MAX_VALUE);
        return new OneTimeIterable(iterator);
    }
}
