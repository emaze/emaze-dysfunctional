package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
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

    public static <T1, T2> Iterable<Pair<T1, T2>> counted(Iterator<T2> iterator, Range<T1> range) {
        return new OneTimeIterable<Pair<T1, T2>>(new CountedIterator<T1, T2>(iterator, range));
    }

    public static <T> Iterable<Pair<Integer, T>> counted(Iterator<T> iterator) {
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();
        final Range<Integer> range = new DenseRange<Integer>(sequencer, comparator, 0, Integer.MAX_VALUE);
        return counted(iterator, range);
    }

    public static <T> Iterable<Pair<Integer, T>> counted(Iterable<T> iterable) {
        dbc.precondition(iterable != null, "cannot call counted with a null iterable");
        return counted(iterable.iterator());
    }

    public static <T1, T2> Iterable<Pair<T1, T2>> counted(Iterable<T2> iterable, Range<T1> range) {
        dbc.precondition(iterable != null, "cannot call counted with a null iterable");
        return counted(iterable.iterator(), range);
    }
}
