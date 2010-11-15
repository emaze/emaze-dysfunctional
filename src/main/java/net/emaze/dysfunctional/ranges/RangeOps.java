package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.emaze.dysfunctional.order.Comparing;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @author rferranti
 */
public class RangeOps {

    private static <T> Range<T> asRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, List<DenseRange<T>> wannaBeRange) {
        if (wannaBeRange.size() == 1) {
            return wannaBeRange.get(0);
        }
        return new SparseRange<T>(sequencer, comparator, wannaBeRange);
    }

    public static <T> Range<T> union(SequencingPolicy<T> sequencer, Comparator<T> comparator, Range<T> lhs, Range<T> rhs) {
        final List<DenseRange<T>> ranges = new ArrayList<DenseRange<T>>();
        ranges.addAll(lhs.densified());
        ranges.addAll(rhs.densified());
        final SortedNonOverlappingRangesTransformer<T> transformer = new SortedNonOverlappingRangesTransformer<T>(sequencer, comparator);
        return asRange(sequencer, comparator, transformer.perform(ranges));
    }

    public static <T> Range<T> intersect(SequencingPolicy<T> sequencer, Comparator<T> comparator, Range<T> lhs, Range<T> rhs) {
        final List<DenseRange<T>> intersection = new ArrayList<DenseRange<T>>();
        for (DenseRange<T> l : lhs.densified()) {
            for (DenseRange<T> r : rhs.densified()) {
                if (!l.overlaps(r)) {
                    continue;
                }
                final Pair<T, T> orderedLowerBounds = Comparing.ordered(l.lower(), r.lower(), comparator);
                final Pair<T, T> orderedUpperBounds = Comparing.ordered(l.upper(), r.upper(), comparator);
                intersection.add(new DenseRange<T>(sequencer, comparator, orderedLowerBounds.second(), orderedUpperBounds.first()));
            }
        }
        final SortedNonOverlappingRangesTransformer<T> transformer = new SortedNonOverlappingRangesTransformer<T>(sequencer, comparator);
        return asRange(sequencer, comparator, transformer.perform(intersection));
    }

    public static <T> Range<T> difference(SequencingPolicy<T> sequencer, Comparator<T> comparator, Range<T> lhs, Range<T> rhs) {
        List<DenseRange<T>> difference = lhs.densified();
        for (DenseRange<T> r : rhs.densified()) {
            difference = difference(sequencer, comparator, difference, r);
        }
        return asRange(sequencer, comparator, difference);
    }

    private static <T> List<DenseRange<T>> difference(SequencingPolicy<T> sequencer, Comparator<T> comparator, List<DenseRange<T>> lhss, DenseRange<T> rhs) {
        final List<DenseRange<T>> difference = new ArrayList<DenseRange<T>>();
        for (DenseRange<T> lhs : lhss) {
            difference.addAll(difference(sequencer, comparator, lhs, rhs));
        }
        return difference;
    }

    private static <T> List<DenseRange<T>> difference(SequencingPolicy<T> sequencer, Comparator<T> comparator, DenseRange<T> lhs, DenseRange<T> rhs) {
        if (!lhs.overlaps(rhs)) {
            return Collections.singletonList(lhs);
        }
        final List<DenseRange<T>> difference = new ArrayList<DenseRange<T>>();
        if (Comparing.lhsIsLesser(lhs.lower(), rhs.lower(), comparator)) {
            difference.add(new DenseRange<T>(sequencer, comparator, lhs.lower(), sequencer.prev(rhs.lower())));
        }
        if (Comparing.lhsIsGreater(lhs.upper(), rhs.upper(), comparator)) {
            difference.add(new DenseRange<T>(sequencer, comparator, sequencer.next(rhs.upper()), lhs.upper()));
        }
        return difference;
    }

    public static <T> Range<T> symmetricDifference(SequencingPolicy<T> sequencer, Comparator<T> comparator, Range<T> lhs, Range<T> rhs) {
        return union(sequencer, comparator,
                difference(sequencer, comparator, lhs, rhs),
                difference(sequencer, comparator, rhs, lhs));
    }
}
