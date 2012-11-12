package net.emaze.dysfunctional.ranges;

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

    private final List<DenseRange<T>> densified;
    private final Comparator<Maybe<T>> comparator;

    public SparseRange(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, List<DenseRange<T>> densified) {
        dbc.precondition(sequencer != null, "trying to create a SparseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a SparseRange<T> with a null Comparator<T>");
        dbc.precondition(densified != null, "trying to create a SparseRange<T> from a null ranges");
        dbc.precondition(!densified.isEmpty(), "trying to create a SparseRange<T> from zero non-empty ranges");
        dbc.precondition(densified.size() > 1, "trying to create a SparseRange<T> when a DenseRange<T> should be created");
        //dbc -> sorted
        //dbc -> non overlapping
        this.densified = densified;
        this.comparator = comparator;
    }

    @Override
    public boolean contains(final T element) {
        dbc.precondition(element != null, "checking if null is contained in SparseRange<T>");
        return new Any<DenseRange<T>>(new RangeNotContaining<T>(element)).accept(densified.iterator());
    }

    @Override
    public T begin() {
        return densified.get(0).begin();
    }

    @Override
    public Maybe<T> end() {
        return densified.get(densified.size() - 1).end();
    }

    @Override
    public int compareTo(Range<T> other) {
        dbc.precondition(other != null, "Comparing (compareTo) a SparseRange<T>(%s) with null");
        return new RangeComparator<T>(comparator).compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(new TransformingIterator<Iterator<T>, DenseRange<T>>(densified.iterator(), new IteratorPlucker<T, DenseRange<T>>()));
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SparseRange == false) {
            return false;
        }
        final SparseRange<T> other = (SparseRange<T>) rhs;
        return this.densified.equals(other.densified);
    }

    @Override
    public int hashCode() {
        return densified.hashCode();
    }

    @Override
    public String toString() {
        final String interposed = new InterposeStrings<DenseRange<T>, String>().perform(densified.iterator(), new SingletonIterator<String>(","));
        return String.format("[%s]", interposed);
    }

    @Override
    public boolean overlaps(final Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a SparseRange<T> and null");
        return new Any<DenseRange<T>>(new RangeOverlappingWith<DenseRange<T>, T>(other)).accept(densified.iterator());
    }

    @Override
    public List<DenseRange<T>> densified() {
        return densified;
    }
}
