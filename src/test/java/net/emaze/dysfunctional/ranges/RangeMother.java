package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.order.JustBeforeNothingComparator;
import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Applications;
import java.util.function.Function;
import net.emaze.dysfunctional.Iterations;
import java.util.Optional;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.NextIntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class RangeMother {

    public static final Integer emptyValue = 0;
    public static final SequencingPolicy<Integer> sequencer = new NextIntegerSequencingPolicy();
    public static final Comparator<Optional<Integer>> comparator = new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>());

    public static DenseRange<Integer> r(int lower, int upper) {
        return new DenseRange<Integer>(sequencer, comparator, Endpoint.Include, lower, Optional.of(upper), Endpoint.Include);
    }

    public static DenseRange<Integer> r(Endpoint left, int lower, int upper, Endpoint right) {
        return new DenseRange<Integer>(sequencer, comparator, left, lower, Optional.of(upper), right);
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
        final Iterator<DenseRange<Integer>> ranges = Applications.transform(pairs, pair -> new DenseRange<Integer>(sequencer, comparator, Endpoint.Include, pair.first(), Optional.of(pair.second()), Endpoint.Include));
        return new SparseRange<Integer>(sequencer, comparator, Consumers.all(ranges));
    }
}
