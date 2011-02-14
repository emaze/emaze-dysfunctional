package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.casts.Casts;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.multiplexing.ChainIterator;
import net.emaze.dysfunctional.delegates.IteratorPlucker;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.strings.Strings;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class SparseRange<T> implements Range<T> {

    private final List<DenseRange<T>> ranges;

    public SparseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, DenseRange<T>... ranges) {
        dbc.precondition(sequencer != null, "trying to create a SparseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a SparseRange<T> with a null Comparator<T>");
        dbc.precondition(ranges != null, "trying to create a SparseRange<T> from null ranges");
        dbc.precondition(ranges.length != 0, "trying to create a SparseRange<T> from zero ranges");
        this.ranges = new SortedNonOverlappingRangesTransformer<T>(sequencer, comparator).perform(Arrays.asList(ranges));
    }

    public SparseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, List<DenseRange<T>> ranges) {
        dbc.precondition(sequencer != null, "trying to create a SparseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a SparseRange<T> with a null Comparator<T>");
        dbc.precondition(ranges != null, "trying to create a SparseRange<T> from a null ranges");
        dbc.precondition(!ranges.isEmpty(), "trying to create a SparseRange<T> from zero ranges");
        this.ranges = new SortedNonOverlappingRangesTransformer<T>(sequencer, comparator).perform(ranges);
    }

    @Override
    public boolean contains(final T element) {
        dbc.precondition(element != null, "checking if null is contained in SparseRange<T>");
        return Iterations.any(ranges, new RangeNotContaining<T>(element));
    }

    @Override
    public T lower() {
        return ranges.get(0).lower();
    }

    @Override
    public T upper() {
        return ranges.get(ranges.size() - 1).upper();
    }

    @Override
    public int compareTo(Range<T> other) {
        dbc.precondition(other != null, "Comparing (compareTo) a SparseRange<T>(%s) with null");
        return new RangeComparator<T>().compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(Iterations.transform(ranges, new IteratorPlucker<T, DenseRange<T>>()));
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SparseRange == false) {
            return false;
        }
        final SparseRange<T> other = Casts.widen(rhs);
        return this.ranges.equals(other.ranges);
    }

    @Override
    public int hashCode() {
        return ranges.hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%s]", Strings.interpose(ranges.iterator(), ","));
    }

    @Override
    public boolean overlaps(final Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a SparseRange<T> and null");
        return Iterations.any(ranges, new RangeNotOverlappingWith<T>(other));
    }

    @Override
    public List<DenseRange<T>> densified() {
        return ranges;
    }
}
