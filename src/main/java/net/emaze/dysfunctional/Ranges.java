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
    private final T emptyValue;

    public Ranges(Comparator<T> comparator, SequencingPolicy<T> sequencer, T emptyValue) {
        dbc.precondition(comparator != null, "cannot create Ranges<T> with a null Comparator<T>");
        dbc.precondition(sequencer != null, "cannot create Ranges<T> with a null SequencingPolicy<T>");
        this.comparator = new JustBeforeNothingComparator<T>(comparator);
        this.sequencer = sequencer;
        this.emptyValue = emptyValue;
    }

    public Range<T> of(Endpoint left, T lower, Maybe<T> upper, Endpoint right) {
        return new DenseRange<T>(sequencer, comparator, left, lower, upper, right);
    }

    /**
     * returns [ lower, upper )
     *
     * @param lower
     * @param upper
     * @return [lower, upper)
     */
    public Range<T> rightHalfOpen(T lower, Maybe<T> upper) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Include, lower, upper, Endpoint.Exclude);
    }
    

    /**
     * returns ( lower, upper ]
     *
     * @param lower
     * @param upper
     * @return (lower, upper]
     */
    public Range<T> leftHalfOpen(T lower, T upper) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Exclude, lower, Maybe.just(upper), Endpoint.Include);
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
     * Creates a singleton Range with the passed value. returns [ value, value ]
     *
     * @param value
     * @return [ lower, upper ]
     */
    public Range<T> degenerate(T value) {
        return new DenseRange<T>(sequencer, comparator, Endpoint.Include, value, Maybe.just(value), Endpoint.Include);
    }

    /**
     * returns [ emptyValue, emptyValue )
     *
     * @param emptyValue
     * @param emptyValue
     * @return [ emptyValue, emptyValue )
     */
    public Range<T> empty() {
        return new DenseRange<T>(this.sequencer, this.comparator, Endpoint.Include, emptyValue, Maybe.just(emptyValue), Endpoint.Exclude);
    }

    public Range<T> union(Range<T> lhs, Range<T> rhs) {
        final Union<T> union = new Union<T>(sequencer, comparator, emptyValue);
        return union.perform(lhs, rhs);
    }

    public Range<T> union(Range<T> first, Range<T> second, Range<T> third) {
        final Union<T> union = new Union<T>(sequencer, comparator, emptyValue);
        return union.perform(union.perform(first, second), third);
    }

    public Range<T> union(Iterator<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot evaluate union for a null iterator of ranges");
        dbc.precondition(ranges.hasNext(), "cannot evaluate union for an empty iterator of ranges");
        final Union<T> union = new Union<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(union, ranges.next()).apply(ranges);
    }

    public Range<T> union(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot evaluate union for a null iterable of ranges");
        dbc.precondition(ranges.iterator().hasNext(), "cannot evaluate union for an empty iterable of ranges");
        final Iterator<Range<T>> iterator = ranges.iterator();
        final Union<T> union = new Union<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(union, iterator.next()).apply(iterator);
    }

    public Range<T> union(Range<T>... ranges) {
        dbc.precondition(ranges != null, "cannot evaluate union for a null array of ranges");
        dbc.precondition(ranges.length != 0, "cannot evaluate union for an empty array of ranges");
        final Iterator<Range<T>> iterator = new ArrayIterator<Range<T>>(ranges);
        final Union<T> union = new Union<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(union, iterator.next()).apply(iterator);
    }

    public Range<T> intersect(Range<T> lhs, Range<T> rhs) {
        final Intersection<T> intersection = new Intersection<T>(sequencer, comparator, emptyValue);
        return intersection.perform(lhs, rhs);
    }

    public Range<T> intersect(Range<T> first, Range<T> second, Range<T> third) {
        final Intersection<T> intersection = new Intersection<T>(sequencer, comparator, emptyValue);
        return intersection.perform(intersection.perform(first, second), third);
    }

    public Range<T> intersect(Iterator<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot intersection a null iterator of ranges");
        dbc.precondition(ranges.hasNext(), "cannot intersection an empty iterator of ranges");
        final Intersection<T> intersection = new Intersection<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(intersection, ranges.next()).apply(ranges);
    }

    public Range<T> intersect(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot intersect a null iterable of ranges");
        dbc.precondition(ranges.iterator().hasNext(), "cannot intersect an empty iterable of ranges");
        final Intersection<T> intersection = new Intersection<T>(sequencer, comparator, emptyValue);
        final Iterator<Range<T>> iterator = ranges.iterator();
        return new Reductor<Range<T>, Range<T>>(intersection, iterator.next()).apply(iterator);
    }

    public Range<T> intersect(Range<T>... ranges) {
        dbc.precondition(ranges != null, "cannot intersect a null array of ranges");
        dbc.precondition(ranges.length != 0, "cannot intersect an empty array of ranges");
        final Intersection<T> intersection = new Intersection<T>(sequencer, comparator, emptyValue);
        final Iterator<Range<T>> iterator = new ArrayIterator<Range<T>>(ranges);
        return new Reductor<Range<T>, Range<T>>(intersection, iterator.next()).apply(iterator);
    }

    public Range<T> symmetricDifference(Range<T> lhs, Range<T> rhs) {
        final SymmetricDifference<T> symmetricDifference = new SymmetricDifference<T>(sequencer, comparator, emptyValue);
        return symmetricDifference.perform(lhs, rhs);
    }

    public Range<T> symmetricDifference(Range<T> first, Range<T> second, Range<T> third) {
        final SymmetricDifference<T> symmetricDifference = new SymmetricDifference<T>(sequencer, comparator, emptyValue);
        return symmetricDifference.perform(symmetricDifference.perform(first, second), third);
    }

    public Range<T> symmetricDifference(Iterator<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot evaluate symmetric difference for a null iterator of ranges");
        dbc.precondition(ranges.hasNext(), "cannot evaluate symmetric difference for an empty iterator of ranges");
        final SymmetricDifference<T> symmetricDifference = new SymmetricDifference<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(symmetricDifference, ranges.next()).apply(ranges);
    }

    public Range<T> symmetricDifference(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot evaluate symmetric difference for a null iterable of ranges");
        dbc.precondition(ranges.iterator().hasNext(), "cannot evaluate symmetric difference for an empty iterable of ranges");
        final Iterator<Range<T>> iterator = ranges.iterator();
        final SymmetricDifference<T> symmetricDifference = new SymmetricDifference<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(symmetricDifference, iterator.next()).apply(iterator);
    }

    public Range<T> symmetricDifference(Range<T>... ranges) {
        dbc.precondition(ranges != null, "cannot evaluate symmetric difference for a null array of ranges");
        dbc.precondition(ranges.length != 0, "cannot evaluate symmetric difference for an empty array of ranges");
        final Iterator<Range<T>> iterator = new ArrayIterator<Range<T>>(ranges);
        final SymmetricDifference<T> symmetricDifference = new SymmetricDifference<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(symmetricDifference, iterator.next()).apply(iterator);
    }

    public Range<T> difference(Range<T> lhs, Range<T> rhs) {
        final Difference<T> difference = new Difference<T>(sequencer, comparator, emptyValue);
        return difference.perform(lhs, rhs);
    }

    public Range<T> difference(Range<T> first, Range<T> second, Range<T> third) {
        final Difference<T> difference = new Difference<T>(sequencer, comparator, emptyValue);
        return difference.perform(difference.perform(first, second), third);
    }

    public Range<T> difference(Iterator<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot evaluate difference for a null iterator of ranges");
        dbc.precondition(ranges.hasNext(), "cannot evaluate difference for an empty iterator of ranges");
        final Difference<T> difference = new Difference<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(difference, ranges.next()).apply(ranges);
    }

    public Range<T> difference(Iterable<Range<T>> ranges) {
        dbc.precondition(ranges != null, "cannot evaluate difference for a null iterable of ranges");
        dbc.precondition(ranges.iterator().hasNext(), "cannot evaluate difference for an empty iterable of ranges");
        final Iterator<Range<T>> iterator = ranges.iterator();
        final Difference<T> difference = new Difference<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(difference, iterator.next()).apply(iterator);
    }

    public Range<T> difference(Range<T>... ranges) {
        dbc.precondition(ranges != null, "cannot evaluate difference for a null array of ranges");
        dbc.precondition(ranges.length != 0, "cannot evaluate difference for an empty array of ranges");
        final Iterator<Range<T>> iterator = new ArrayIterator<Range<T>>(ranges);
        final Difference<T> difference = new Difference<T>(sequencer, comparator, emptyValue);
        return new Reductor<Range<T>, Range<T>>(difference, iterator.next()).apply(iterator);
    }
}
