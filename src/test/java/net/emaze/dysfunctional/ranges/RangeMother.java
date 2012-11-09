package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.NextIntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class RangeMother {

    public static final SequencingPolicy<Integer> sequencer = new NextIntegerSequencingPolicy();
    public static final Comparator<Integer> comparator = new ComparableComparator<Integer>();

    public static DenseRange<Integer> r(int lower, int upper) {
        return new DenseRange<Integer>(sequencer, comparator, Endpoints.IncludeBoth, lower, upper);
    }
    
    public static DenseRange<Integer> r(Endpoints endpoints, int lower, int upper) {
        return new DenseRange<Integer>(sequencer, comparator, endpoints, lower, upper);
    }

    public static Pair<Integer, Integer> p(int lower, int upper) {
        return Pair.of(lower, upper);
    }

    public static SparseRange<Integer> r(Pair<Integer, Integer> theOne) {
        return sparse(Iterations.iterator(theOne));
    }

    public static SparseRange<Integer> r(Pair<Integer, Integer> former, Pair<Integer, Integer> latter) {
        return sparse(Iterations.iterator(former, latter));
    }

    public static SparseRange<Integer> r(Pair<Integer, Integer> first, Pair<Integer, Integer> second, Pair<Integer, Integer> third) {
        return sparse(Iterations.iterator(first, second, third));
    }

    private static SparseRange<Integer> sparse(Iterator<Pair<Integer, Integer>> pairs) {
        final Iterator<DenseRange<Integer>> ranges = Applications.transform(pairs, new Delegate<DenseRange<Integer>, Pair<Integer, Integer>>() {

            @Override
            public DenseRange<Integer> perform(Pair<Integer, Integer> pair) {
                return new DenseRange<Integer>(sequencer, comparator, Endpoints.IncludeBoth, pair.first(), pair.second());
            }
        });
        return new SparseRange<Integer>(sequencer, comparator, Consumers.all(ranges));
    }
}
