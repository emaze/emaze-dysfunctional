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
public class SparseRange<T extends Comparable<T>> implements Range<T> {

    private final List<Range<T>> ranges = new ArrayList<Range<T>>();
    private final RangePolicy<T> policy;

    public SparseRange(RangePolicy<T> policy, Range<T>... ranges) {
        dbc.precondition(ranges.length != 0, "SparseRange<T> must be constructed with at least one argument");
        this.policy = policy;
        this.ranges.addAll(policy.normalize(ranges));
    }

    @Override
    public boolean contains(final T element) {
        return Iterations.any(ranges, new Predicate<Range<T>>() {

            @Override
            public boolean test(Range<T> range) {
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
        return policy.compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(Iterations.map(ranges, new Delegate<Iterator<T>, Range<T>>() {

            @Override
            public Iterator<T> perform(Range<T> iterable) {
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
}
