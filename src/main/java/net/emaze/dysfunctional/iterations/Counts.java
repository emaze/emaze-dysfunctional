package net.emaze.dysfunctional.iterations;

import java.util.Comparator;
import net.emaze.dysfunctional.concepts.ComparableComparator;
import net.emaze.dysfunctional.iterations.sequencing.IntegerSequencingPolicy;
import net.emaze.dysfunctional.iterations.sequencing.SequencingPolicy;
import net.emaze.dysfunctional.ranges.DenseRange;
import net.emaze.dysfunctional.ranges.Range;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * 
 * @author rferranti
 */
public class Counts {

    public static <T> Iterable<Pair<Integer, T>> counted(Iterable<T> iterable) {
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();
        final Range<Integer> range = new DenseRange<Integer>(sequencer, comparator, 0, Integer.MAX_VALUE);
        return counted(iterable, range);
    }

    public static <T1, T2> Iterable<Pair<T1, T2>> counted(Iterable<T2> iterable, Range<T1> range) {
        return new OneTimeIterable(new CountedIterator(iterable.iterator(), range));
    }
}
