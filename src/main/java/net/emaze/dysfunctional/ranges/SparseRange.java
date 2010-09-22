package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.order.Comparing;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.multiplexing.ChainIterator;
import net.emaze.dysfunctional.iterations.IterableToIteratorTransformer;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 *
 * @author rferranti
 */
public class SparseRange<T> implements Range<T> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<T> comparator;
    private final List<DenseRange<T>> ranges = new ArrayList<DenseRange<T>>();

    public SparseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, DenseRange<T>... ranges) {
        dbc.precondition(sequencer != null, "trying to create a SparseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a SparseRange<T> with a null Comparator<T>");
        dbc.precondition(ranges.length != 0, "trying to create a SparseRange<T> from zero ranges");
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.ranges.addAll(asNonOverlapping(ranges));
    }

    private List<DenseRange<T>> asNonOverlapping(DenseRange<T>... ranges) {
        final List<DenseRange<T>> sortedRanges = Arrays.asList(ranges);
        final List<DenseRange<T>> out = new ArrayList<DenseRange<T>>();
        Collections.sort(sortedRanges, new RangeComparator());
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
                out.add(current);
                current = next;
            }
        }
        out.add(current);
        return out;
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
        return new RangeComparator().compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(Iterations.map(ranges, new IterableToIteratorTransformer<T,DenseRange<T>>()));
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SparseRange == false) {
            return false;
        }
        final SparseRange<T> other = (SparseRange<T>) rhs;
        return new EqualsBuilder().append(this.sequencer, other.sequencer).
                append(this.comparator, other.comparator).
                append(this.ranges, other.ranges).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sequencer).
                append(comparator).
                append(ranges).
                toHashCode();
    }

    @Override
    public boolean overlaps(final Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a SparseRange<T> and null");
        return Iterations.any(ranges, new RangeNotOverlappingWith<T>(other));
    }
}
