package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.order.Comparing;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * transforms a list of DenseRange to an equivalent list of sorted ranges with no overlapping ranges
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
            if (Comparing.sameOrder(sequencer.next(current.upper()), next.upper(), comparator)
                    || (current.overlaps(next) && Comparing.lhsIsGreater(next.upper(), current.upper(), comparator))) {
                // |-----------|
                //          |--------|
                // or
                // |-----------|
                //              |-----|
                current = new DenseRange<T>(sequencer, comparator, current.lower(), next.upper());
            } else {
                // |--|
                //       |---|
                sortedNonOverlappingRanges.add(current);
                current = next;
            }
        }
        sortedNonOverlappingRanges.add(current);
        return sortedNonOverlappingRanges;
    }
}
