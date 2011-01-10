package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.iterations.OneTimeIterable;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * 
 * @author rferranti
 */
public abstract class Counts {

    public static <T> Iterable<Pair<Integer, T>> counted(Iterable<T> iterable) {
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();
        final Range<Integer> range = new DenseRange<Integer>(sequencer, comparator, 0, Integer.MAX_VALUE);
        return counted(iterable, range);
    }

    public static <T1, T2> Iterable<Pair<T1, T2>> counted(Iterable<T2> iterable, Range<T1> range) {
        return new OneTimeIterable<Pair<T1, T2>>(new CountedIterator<T1, T2>(iterable.iterator(), range));
    }
}
