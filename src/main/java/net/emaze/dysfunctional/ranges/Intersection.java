package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.MakeOrder;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * {@code A ∩ B}.
 * @author rferranti
 */
public class Intersection<T> implements BinaryDelegate<Range<T>, Range<T>, Range<T>> {
    private final SequencingPolicy<T> sequencer;
    private final Comparator<Maybe<T>> comparator;
    private final T emptyValue;

    public Intersection(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.emptyValue = emptyValue;
    }

    @Override
    public Range<T> perform(Range<T> lhs, Range<T> rhs) {
        final List<DenseRange<T>> intersection = new ArrayList<DenseRange<T>>();
        final MakeOrder<Maybe<T>> makeOrder = new MakeOrder<Maybe<T>>(comparator);
        for (DenseRange<T> l : lhs.densified()) {
            for (DenseRange<T> r : rhs.densified()) {
                if (!l.overlaps(r)) {
                    continue;
                }
                final Pair<Maybe<T>, Maybe<T>> orderedLowerBounds = makeOrder.perform(Maybe.just(l.begin()), Maybe.just(r.begin()));
                final Pair<Maybe<T>, Maybe<T>> orderedUpperBounds = makeOrder.perform(l.end(), r.end());
                intersection.add(new DenseRange<T>(sequencer, comparator, Endpoint.Include, orderedLowerBounds.second().value(), orderedUpperBounds.first(), Endpoint.Exclude));
            }
        }
        return new MakeRange<T>(sequencer, comparator, emptyValue).perform(intersection);
    }

}
