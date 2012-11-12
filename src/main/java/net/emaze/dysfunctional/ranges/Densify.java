package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Negator;
import net.emaze.dysfunctional.filtering.FilteringIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.Max;
import net.emaze.dysfunctional.order.Order;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;

/**
 * Transforms a list of {@code DenseRange<T>} to an equivalent {@code List} (possibly empty) of
 * sorted, non-overlapping, non-empty ranges.
 *
 * @param <T>
 * @author rferranti
 */
public class Densify<T> implements Delegate<List<DenseRange<T>>, List<DenseRange<T>>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<Maybe<T>> comparator;

    public Densify(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator) {
        this.sequencer = sequencer;
        this.comparator = comparator;
    }

    @Override
    public List<DenseRange<T>> perform(List<DenseRange<T>> ranges) {
        final SortedSet<DenseRange<T>> sortedRanges = new TreeSet<DenseRange<T>>(ranges);
        final List<DenseRange<T>> sortedNonOverlappingRanges = new ArrayList<DenseRange<T>>();
        final Iterator<DenseRange<T>> nonEmptyRanges = new FilteringIterator<DenseRange<T>>(sortedRanges.iterator(), new Negator<DenseRange<T>>(new RangeIsEmpty<DenseRange<T>, T>()));
        if (!nonEmptyRanges.hasNext()) {
            return Collections.emptyList();
        }
        DenseRange<T> current = nonEmptyRanges.next();
        while (nonEmptyRanges.hasNext()) {
            final DenseRange<T> next = nonEmptyRanges.next();
            if (canBeMerged(current, next)) {
                final Maybe<T> max = new Max<Maybe<T>>(comparator).perform(current.end(), next.end());
                current = new DenseRange<T>(sequencer, comparator, Endpoint.Include, current.begin(), max, Endpoint.Exclude);
            } else {
                sortedNonOverlappingRanges.add(current);
                current = next;
            }
        }
        sortedNonOverlappingRanges.add(current);
        return sortedNonOverlappingRanges;
    }

    /**
     * <code>
     * |-----------|
     *             |-----|
     * </code> or
     * <code>
     * |-----------|
     *            |------|
     * </code>
     *
     * @param current the current range
     * @param next the next range
     * @return true if the two ranges can be merged
     */
    private boolean canBeMerged(DenseRange<T> current, DenseRange<T> next) {
        return Order.of(comparator, current.end(), Maybe.just(next.begin())) == Order.EQ || current.overlaps(next);
    }
}
