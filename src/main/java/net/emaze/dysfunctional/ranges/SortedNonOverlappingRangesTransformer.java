package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import net.emaze.dysfunctional.Comparing;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * transforms a list of DenseRange to an equivalent list of sorted ranges with no overlapping ranges
 * @param <T>
 * @author rferranti
 */
public class SortedNonOverlappingRangesTransformer<T> implements Delegate<List<DenseRange<T>>, List<DenseRange<T>>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<T> comparator;

    public SortedNonOverlappingRangesTransformer(SequencingPolicy<T> sequencer, Comparator<T> comparator) {
        this.sequencer = sequencer;
        this.comparator = comparator;
    }

    @Override
    public List<DenseRange<T>> perform(List<DenseRange<T>> ranges) {
        final SortedSet<DenseRange<T>> sortedRanges = new TreeSet<DenseRange<T>>(new RangeComparator<T>());
        sortedRanges.addAll(ranges);
        final List<DenseRange<T>> sortedNonOverlappingRanges = new ArrayList<DenseRange<T>>();
        final Iterator<DenseRange<T>> iter = sortedRanges.iterator();
        DenseRange<T> current = iter.next();
        while (iter.hasNext()) {
            final DenseRange<T> next = iter.next();
            if (canBeMerged(current, next)) {
                current = new DenseRange<T>(sequencer, comparator, current.lower(), Comparing.max(current.upper(), next.upper(), comparator));
            } else {
                sortedNonOverlappingRanges.add(current);
                current = next;
            }
        }
        sortedNonOverlappingRanges.add(current);
        return sortedNonOverlappingRanges;
    }

    /**
     * |-----------|
     *              |-----|
     * or
     * |-----------|
     *           |--------|
     * @param current the current range
     * @param next the next range
     * @return true if the two ranges can be merged
     */
    private boolean canBeMerged(DenseRange<T> current, DenseRange<T> next) {
        return Comparing.sameOrder(sequencer.next(current.upper()), next.lower(), comparator) || current.overlaps(next);
    }
}
