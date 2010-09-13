package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.iterations.RangeIterator;
import java.util.Iterator;
import net.emaze.dysfunctional.concepts.EqualsBuilder;
import net.emaze.dysfunctional.concepts.HashCodeBuilder;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class DenseRange<T extends Comparable<T>> implements Range<T> {

    private final T lower;
    private final T upper;
    private final RangePolicy<T> policy;

    public DenseRange(RangePolicy<T> policy, T lower, T upper) {
        dbc.precondition(policy != null, "trying to create a DenseRange with a null RangePolicy");
        dbc.precondition(lower != null, "trying to create a DenseRange with null lower bound");
        dbc.precondition(upper != null, "trying to create a DenseRange with null upper bound");
        this.policy = policy;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public boolean contains(T element) {
        return policy.contains(this, element);
    }

    @Override
    public T lower() {
        return lower;
    }

    @Override
    public T upper() {
        return upper;
    }

    @Override
    public int compareTo(Range<T> other) {
        return policy.compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new RangeIterator(policy, lower, upper);
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof DenseRange == false) {
            return false;
        }
        final DenseRange<T> other = (DenseRange<T>) rhs;
        return new EqualsBuilder().
                append(this.policy, other.policy).
                append(this.lower, other.lower).
                append(this.upper, other.upper).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(policy).
                append(lower).
                append(upper).
                toHashCode();
    }
}
