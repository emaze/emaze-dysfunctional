package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.Order;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class DenseRange<T> implements Range<T> {

    private final SequencingPolicy<T> sequencer;
    final T lower;
    final T upper;
    private final Endpoints endpoints;
    private final Comparator<T> innerComparator;
    private final Comparator<Maybe<T>> comparator;
    private final T first;
    private final Maybe<T> afterLast;

    public DenseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, T lower, T upper) {
        this(sequencer, comparator, Endpoints.IncludeBoth, lower, upper);
    }
    
    public DenseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, Endpoints endpoints, T lower, T upper) {
        dbc.precondition(sequencer != null, "trying to create a DenseRange<T> with a null SequencingPolicy<T>");
        dbc.precondition(comparator != null, "trying to create a DenseRange<T> with a null Comparator<T>");
        dbc.precondition(lower != null, "trying to create a DenseRange<T> with null lower bound");
        dbc.precondition(upper != null, "trying to create a DenseRange<T> with null upper bound");
        dbc.precondition(Order.of(comparator, lower, upper) != Order.GT, "trying to create a DenseRange<T> a lower bound greater than upper bound");
        this.sequencer = sequencer;
        this.innerComparator = comparator;
        this.comparator = new NothingIsGreatestComparator<T>(comparator);
        this.endpoints = endpoints;
        this.lower = lower;
        this.upper = upper;

        this.first = endpoints.includesLeft() ? lower : sequencer.next(lower).value();
        this.afterLast = endpoints.includesRight() ? sequencer.next(upper) : Maybe.just(upper);
    }
    
    DenseRange(SequencingPolicy<T> sequencer, Comparator<T> comparator, T first) {
        this.sequencer = sequencer;
        this.innerComparator = comparator;
        this.comparator = new NothingIsGreatestComparator<T>(comparator);
        this.endpoints = Endpoints.IncludeLeft;
        this.lower = first;
        this.upper = null;

        this.first = first;
        this.afterLast = Maybe.nothing();
    }

    @Override
    public boolean contains(T element) {
        dbc.precondition(element != null, "checking if null is contained in DenseRange<T>");
        return Order.of(innerComparator, element, first).isGte() && Order.of(comparator, Maybe.just(element), afterLast).isLt();
    }

    @Override
    public T first() {
        return first;
    }

    @Override
    public Maybe<T> afterLast() {
        return afterLast;
    }

    @Override
    public int compareTo(Range<T> other) {
        dbc.precondition(other != null, "comparing (compareTo) a DenseRange<T> with null");
        return new RangeComparator<T>(innerComparator).compare(this, other);
    }

    @Override
    public Iterator<T> iterator() {
        return new RangeIterator<T>(sequencer, innerComparator, endpoints, first, afterLast);
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof DenseRange == false) {
            return false;
        }
        final DenseRange<T> other = (DenseRange<T>) rhs;
        if (isEmpty()) {
            return true;
        }
        return new EqualsBuilder().append(this.sequencer, other.sequencer).
                append(this.innerComparator, other.innerComparator).
                append(this.first, other.first).
                append(this.afterLast, other.afterLast).
                isEquals();
    }

    public boolean isEmpty() {
        return this.afterLast.hasValue() && this.first.equals(this.afterLast.value());
    }

    @Override
    public int hashCode() {
        if (isEmpty()) {
            return new HashCodeBuilder().append(sequencer).
                append(innerComparator).
                toHashCode();
        }
        return new HashCodeBuilder().append(sequencer).
                append(innerComparator).
                append(first).
                append(afterLast).
                toHashCode();
    }

    @Override
    public String toString() {
        return endpoints.stringify(this);
    }

    /**
     * Apples to apples (dense to dense) : yields false if this.lower >
     * other.upper or other.lower > this.upper Apples to oranges: (dense to
     * nonDense) yields nonDense.overlaps(dense)
     *
     * @param other
     * @return
     */
    @Override
    public boolean overlaps(Range<T> other) {
        dbc.precondition(other != null, "checking for overlaps between a DenseRange<T> and null");
        if (other instanceof DenseRange == false) {
            return other.overlaps(this);
        }
        if (Order.of(comparator, Maybe.just(this.first()), other.afterLast()).isGte() || Order.of(comparator, Maybe.just(other.first()), this.afterLast()).isGte()) {
            return false;
        }
        return true;
    }

    @Override
    public List<DenseRange<T>> densified() {
        final List<DenseRange<T>> out = new ArrayList<DenseRange<T>>();
        out.add(this);
        return out;
    }
}
