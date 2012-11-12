package net.emaze.dysfunctional;

import java.util.Comparator;
import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.JustBeforeNothingComparator;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.DenseRange;
import net.emaze.dysfunctional.ranges.Difference;
import net.emaze.dysfunctional.ranges.Intersection;
import net.emaze.dysfunctional.ranges.Range;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import net.emaze.dysfunctional.ranges.SymmetricDifference;
import net.emaze.dysfunctional.ranges.Union;
import net.emaze.dysfunctional.reductions.Reductor;

/**
 *
 * @author rferranti
 */
public class Ranges<T> {

    private final Comparator<Maybe<T>> comparator;
    private final SequencingPolicy<T> sequencer;
    private final DenseRange<T> empty;
    private final Union<T> union;
    private final Intersection<T> intersection;
    private final Difference<T> difference;
    private final SymmetricDifference<T> symmetricDifference;

    public Ranges(Comparator<T> comparator, SequencingPolicy<T> sequencer, T emptyValue) {
        this.comparator = new JustBeforeNothingComparator<T>(comparator);
        this.sequencer = sequencer;
        this.empty = new DenseRange<T>(this.sequencer, this.comparator, Endpoint.Include, emptyValue, Maybe.just(emptyValue), Endpoint.Exclude);
        this.union = new Union<T>(this.sequencer, this.comparator, emptyValue);
        this.intersection = new Intersection<T>(this.sequencer, this.comparator, emptyValue);
        this.difference = new Difference<T>(this.sequencer, this.comparator, emptyValue);
        this.symmetricDifference = new SymmetricDifference<T>(this.sequencer, this.comparator, emptyValue);
    }

    public Range<T> of(Endpoint left, T lower, T upper, Endpoint right) {
        return new DenseRange<T>(sequencer, comparator, left, lower, Maybe.just(upper), right);
    }

    /**
     * returns ( lower, upper )
     *
     * @param lower
     * @param upper
     * @return (lower, upper)
     */
    public Range<T> open(T lower, T upper) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Exclude, lower, Maybe.just(upper), Endpoint.Exclude);
    }

    /**
     * returns [ lower, upper ]
     *
     * @param lower
     * @param upper
     * @return [ lower, upper ]
     */
    public Range<T> closed(T lower, T upper) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Include, lower, Maybe.just(upper), Endpoint.Include);
    }

    /**
     * returns [ value, value ]
     *
     * @param value
     * @param value
     * @return [ lower, upper ]
     */
    public Range<T> degenerate(T value) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Include, value, Maybe.just(value), Endpoint.Include);
    }

    /**
     * returns [ value, value )
     *
     * @param value
     * @param value
     * @return [ value, value )
     */
    public Range<T> empty(T value) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Include, value, Maybe.just(value), Endpoint.Exclude);
    }

    /**
     * returns [ emptyValue, emptyValue )
     *
     * @param emptyValue
     * @param emptyValue
     * @return [ emptyValue, emptyValue )
     */
    public Range<T> empty() {
        return empty;
    }

    public Range<T> union(Range<T> lhs, Range<T> rhs) {
        return union.perform(lhs, rhs);
    }

    public Range<T> union(Range<T> first, Range<T> second, Range<T> third) {
        return union.perform(union.perform(first, second), third);
    }

    public Range<T> union(Iterator<Range<T>> ranges) {
        dbc.precondition(ranges.hasNext(), "empty iterator");
        return new Reductor<Range<T>, Range<T>>(union, ranges.next()).perform(ranges);
    }

    public Range<T> union(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "null iterable");
        return union(ranges.iterator());
    }

    public Range<T> union(Range<T>... ranges) {
        dbc.precondition(ranges != null, "null iterable");
        return union(new ArrayIterator<Range<T>>(ranges));
    }

    public Range<T> intersect(Range<T> lhs, Range<T> rhs) {
        return intersection.perform(lhs, rhs);
    }

    public Range<T> intersect(Range<T> first, Range<T> second, Range<T> third) {
        return intersection.perform(intersection.perform(first, second), third);
    }

    public Range<T> intersect(Iterator<Range<T>> ranges) {
        dbc.precondition(ranges.hasNext(), "empty iterator");
        return new Reductor<Range<T>, Range<T>>(intersection, ranges.next()).perform(ranges);
    }

    public Range<T> intersect(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "null iterable");
        return intersect(ranges.iterator());
    }

    public Range<T> intersect(Range<T>... ranges) {
        dbc.precondition(ranges != null, "null array");
        return intersect(new ArrayIterator<Range<T>>(ranges));
    }

    public Range<T> symmetricDifference(Range<T> lhs, Range<T> rhs) {
        return symmetricDifference.perform(lhs, rhs);
    }

    public Range<T> symmetricDifference(Range<T> first, Range<T> second, Range<T> third) {
        return symmetricDifference.perform(symmetricDifference.perform(first, second), third);
    }

    public Range<T> symmetricDifference(Iterator<Range<T>> ranges) {
        return new Reductor<Range<T>, Range<T>>(symmetricDifference, ranges.next()).perform(ranges);
    }

    public Range<T> symmetricDifference(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "null iterable");
        return symmetricDifference(ranges.iterator());
    }

    public Range<T> symmetricDifference(Range<T>... ranges) {
        dbc.precondition(ranges != null, "null array");
        return symmetricDifference(new ArrayIterator<Range<T>>(ranges));
    }

    public Range<T> difference(Range<T> lhs, Range<T> rhs) {
        return difference.perform(lhs, rhs);
    }
}
