package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.concepts.Comparing;
import net.emaze.dysfunctional.concepts.EqualsBuilder;
import net.emaze.dysfunctional.concepts.HashCodeBuilder;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.iterations.ChainIterator;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.iterations.sequencing.SequencingPolicy;

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
            DenseRange<T> next = iter.next();
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
        return Iterations.any(ranges, new Predicate<DenseRange<T>>() {

            @Override
            public boolean test(DenseRange<T> range) {
                return range.contains(element);
            }
        });
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
        return new ChainIterator<T>(Iterations.map(ranges, new Delegate<Iterator<T>, DenseRange<T>>() {

            @Override
            public Iterator<T> perform(DenseRange<T> iterable) {
                return iterable.iterator();
            }
        }));
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
        return Iterations.any(ranges, new Predicate<DenseRange<T>>() {

            @Override
            public boolean test(DenseRange<T> range) {
                return range.overlaps(other);
            }
        });
    }
}
