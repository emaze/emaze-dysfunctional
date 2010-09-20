package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.iterations.RangeIterator;
import java.util.Iterator;
import net.emaze.dysfunctional.concepts.Comparing;
import net.emaze.dysfunctional.concepts.EqualsBuilder;
import net.emaze.dysfunctional.concepts.HashCodeBuilder;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public class DenseRange<T> implements Range<T> {

    private final T lower;
    private final T upper;
    private final RangePolicy<T> policy;

    public DenseRange(RangePolicy<T> policy, T lower, T upper) {
        dbc.precondition(policy != null, "trying to create a DenseRange<T> with a null RangePolicy");
        dbc.precondition(lower != null, "trying to create a DenseRange<T> with null lower bound");
        dbc.precondition(upper != null, "trying to create a DenseRange<T> with null upper bound");
        this.policy = policy;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public boolean contains(T element) {
        dbc.precondition(element != null, "checking if null is contained in DenseRange<T>(%s)", policy.toString(this));
        return Comparing.lhsIsLesser(lower, element, policy) && Comparing.lhsIsLesser(element, upper, policy);
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
        dbc.precondition(other != null, "Comparing (compareTo) a DenseRange<T>(%s) with null", policy.toString(this));
        return new RangeComparator().compare(this, other);
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
        return new EqualsBuilder().append(this.policy, other.policy).
                append(this.lower, other.lower).
                append(this.upper, other.upper).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(policy).
                append(lower).
                append(upper).
                toHashCode();
    }

    /**
     * Apples to apples (dense to dense) :
     * * this.upper > other.lower yields false
     *   lhs       |--------|
     *   rhs |----|
     * * this.lower > other.upper yields false
     *   lhs |--------|
     *   rhs           |----|
     * else: yields true
     * Apples to oranges: (dense to nonDense)
     *   yields nonDense.overlaps(dense)
     */
    @Override
    public boolean overlaps(Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a DenseRange<T>(%s) and null", policy.toString(this));
        if (other instanceof DenseRange == false) {
            return other.overlaps(this);
        }
        if (Comparing.lhsIsGreater(upper, other.lower(), policy) || Comparing.lhsIsGreater(lower, other.upper(), policy)) {
            return false;
        }
        return true;
    }
}
