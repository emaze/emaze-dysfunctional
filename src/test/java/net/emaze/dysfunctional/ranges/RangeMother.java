package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class RangeMother {
    public static final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
    public static final Comparator<Integer> comparator = new ComparableComparator<Integer>();


    public static DenseRange<Integer> r(int lower, int upper) {
        return new DenseRange<Integer>(sequencer, comparator, lower, upper);
    }

    public static Pair<Integer, Integer> p(int lower, int upper) {
        return new Pair<Integer, Integer>(lower, upper);
    }

    public static SparseRange<Integer> r(Pair<Integer, Integer>... pairs) {
        final Iterator<DenseRange<Integer>> ranges = Iterations.transform(pairs, new Delegate<DenseRange<Integer>, Pair<Integer, Integer>>() {

            @Override
            public DenseRange<Integer> perform(Pair<Integer, Integer> pair) {
                return new DenseRange<Integer>(sequencer, comparator, pair.first(), pair.second());
            }
        });
        return new SparseRange<Integer>(sequencer, comparator, Consumers.all(ranges));
    }
}
