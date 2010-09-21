package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * 
 * @author rferranti
 */
public class Counts {

    public static <T> Iterable<Pair<Integer, T>> counted(Iterable<T> iterable) {
        return counted(iterable, 0, Integer.MAX_VALUE);
    }

    public static <T> Iterable<Pair<Integer, T>> counted(Iterable<T> iterable, int from, int upTo) {
        final Iterator<T> iterator = iterable.iterator();
        return new OneTimeIterable(new CountedIterator(iterator, from, upTo));
    }
}
