package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.Order;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;

/**
 * A \ B.
 * 
 * @author rferranti
 */
public class Difference<T> implements BinaryDelegate<Range<T>, Range<T>, Range<T>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<Maybe<T>> comparator;
    private final T emptyValue;

    public Difference(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.emptyValue = emptyValue;
    }

    @Override
    public Range<T> perform(Range<T> lhs, Range<T> rhs) {
        List<DenseRange<T>> difference = lhs.densified();
        for (DenseRange<T> r : rhs.densified()) {
            difference = difference(sequencer, comparator, difference, r);
        }
        return new MakeRange<T>(sequencer, comparator, emptyValue).perform(difference);
    }

    private static <T> List<DenseRange<T>> difference(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, List<DenseRange<T>> lhss, DenseRange<T> rhs) {
        final List<DenseRange<T>> difference = new ArrayList<DenseRange<T>>();
        for (DenseRange<T> lhs : lhss) {
            difference.addAll(difference(sequencer, comparator, lhs, rhs));
        }
        return difference;
    }

    private static <T> List<DenseRange<T>> difference(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, DenseRange<T> lhs, DenseRange<T> rhs) {
        if (!lhs.overlaps(rhs)) {
            return Collections.singletonList(lhs);
        }
        final List<DenseRange<T>> difference = new ArrayList<DenseRange<T>>();
        if (Order.of(comparator, Maybe.just(lhs.begin()), Maybe.just(rhs.begin())) == Order.LT) {
            difference.add(new DenseRange<T>(sequencer, comparator, Endpoint.Include, lhs.begin(), Maybe.just(rhs.begin()), Endpoint.Exclude));
        }
        if (Order.of(comparator, lhs.end(), rhs.end()) == Order.GT) {
            difference.add(new DenseRange<T>(sequencer, comparator, Endpoint.Include, rhs.end().value(), lhs.end(), Endpoint.Exclude));
        }
        return difference;
    }
}
