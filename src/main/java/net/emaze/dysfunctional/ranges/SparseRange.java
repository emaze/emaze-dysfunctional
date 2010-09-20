package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.concepts.EqualsBuilder;
import net.emaze.dysfunctional.concepts.HashCodeBuilder;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.iterations.ChainIterator;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public class SparseRange<T> implements Range<T> {

    private final List<DenseRange<T>> ranges = new ArrayList<DenseRange<T>>();
    private final RangePolicy<T> policy;

    public SparseRange(RangePolicy<T> policy, DenseRange<T>... ranges) {
        dbc.precondition(policy != null, "trying to create a SparseRange<T> with a null RangePolicy");
        dbc.precondition(ranges.length != 0, "trying to create a SparseRange<T> from zero ranges");
        this.policy = policy;
        this.ranges.addAll(policy.asNonOverlapping(ranges));
    }

    @Override
    public boolean contains(final T element) {
        dbc.precondition(element != null, "checking if null is contained in SparseRange<T>(%s)", policy.toString(ranges));
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
        dbc.precondition(other != null, "Comparing (compareTo) a SparseRange<T>(%s) with null", policy.toString(ranges));
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
        return new EqualsBuilder().append(this.policy, other.policy).
                append(this.ranges, other.ranges).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(policy).
                append(this.ranges).
                toHashCode();
    }

    @Override
    public boolean overlaps(final Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a SparseRange<T>(%s) and null", policy.toString(ranges));
        return Iterations.any(ranges, new Predicate<DenseRange<T>>() {

            @Override
            public boolean test(DenseRange<T> range) {
                return range.overlaps(other);
            }
        });
    }
}
