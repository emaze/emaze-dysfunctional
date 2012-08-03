package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.IteratorPlucker;
import net.emaze.dysfunctional.iterations.SingletonIterator;
import net.emaze.dysfunctional.iterations.TransformingIterator;
import net.emaze.dysfunctional.multiplexing.ChainIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.reductions.Any;
import net.emaze.dysfunctional.strings.InterposeStrings;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class SparseRange<T> implements Range<T> {

    private final List<DenseRange<T>> ranges;
    private final Comparator<T> comparator;

    public SparseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, DenseRange<T>... ranges) {
        dbc.precondition(sequencer != null, "trying to create a SparseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a SparseRange<T> with a null Comparator<T>");
        dbc.precondition(ranges != null, "trying to create a SparseRange<T> from null ranges");
        dbc.precondition(ranges.length != 0, "trying to create a SparseRange<T> from zero ranges");
        this.ranges = new SortedNonOverlappingRangesTransformer<T>(sequencer, comparator).perform(Arrays.asList(ranges));
        this.comparator = comparator;
    }

    public SparseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, List<DenseRange<T>> ranges) {
        dbc.precondition(sequencer != null, "trying to create a SparseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a SparseRange<T> with a null Comparator<T>");
        dbc.precondition(ranges != null, "trying to create a SparseRange<T> from a null ranges");
        dbc.precondition(!ranges.isEmpty(), "trying to create a SparseRange<T> from zero ranges");
        this.ranges = new SortedNonOverlappingRangesTransformer<T>(sequencer, comparator).perform(ranges);
        this.comparator = comparator;
    }

    @Override
    public boolean contains(final T element) {
        dbc.precondition(element != null, "checking if null is contained in SparseRange<T>");
        return new Any<DenseRange<T>>(new RangeNotContaining<T>(element)).accept(ranges.iterator());
    }

    @Override
    public T first() {
        return ranges.get(0).first();
    }

    @Override
    public Maybe<T> afterLast() {
        return ranges.get(ranges.size() - 1).afterLast();
    }

    @Override
    public int compareTo(Range<T> other) {
        dbc.precondition(other != null, "Comparing (compareTo) a SparseRange<T>(%s) with null");
        return new RangeComparator<T>(comparator).compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(new TransformingIterator<Iterator<T>, DenseRange<T>>(ranges.iterator(), new IteratorPlucker<T, DenseRange<T>>()));
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SparseRange == false) {
            return false;
        }
        final SparseRange<T> other = (SparseRange<T>) rhs;
        return this.ranges.equals(other.ranges);
    }

    @Override
    public int hashCode() {
        return ranges.hashCode();
    }

    @Override
    public String toString() {
        final String interposed = new InterposeStrings<DenseRange<T>, String>().perform(ranges.iterator(), new SingletonIterator<String>(","));
        return String.format("[%s]", interposed);
    }

    @Override
    public boolean overlaps(final Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a SparseRange<T> and null");
        return new Any<DenseRange<T>>(new RangeNotOverlappingWith<T>(other)).accept(ranges.iterator());
    }

    @Override
    public List<DenseRange<T>> densified() {
        return ranges;
    }
}
