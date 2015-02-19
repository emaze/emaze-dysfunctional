package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.Optional;
import net.emaze.dysfunctional.order.MakeOrder;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * {@code A ∩ B}.
 * @author rferranti
 */
public class Intersection<T> implements BinaryOperator<Range<T>> {
    private final SequencingPolicy<T> sequencer;
    private final Comparator<Optional<T>> comparator;
    private final T emptyValue;

    public Intersection(SequencingPolicy<T> sequencer, Comparator<Optional<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.emptyValue = emptyValue;
    }

    @Override
    public Range<T> apply(Range<T> lhs, Range<T> rhs) {
        final List<DenseRange<T>> intersection = new ArrayList<DenseRange<T>>();
        final MakeOrder<Optional<T>> makeOrder = new MakeOrder<Optional<T>>(comparator);
        for (DenseRange<T> l : lhs.densified()) {
            for (DenseRange<T> r : rhs.densified()) {
                if (!l.overlaps(r)) {
                    continue;
                }
                final Pair<Optional<T>, Optional<T>> orderedLowerBounds = makeOrder.apply(Optional.of(l.begin()), Optional.of(r.begin()));
                final Pair<Optional<T>, Optional<T>> orderedUpperBounds = makeOrder.apply(l.end(), r.end());
                intersection.add(new DenseRange<T>(sequencer, comparator, Endpoint.Include, orderedLowerBounds.second().get(), orderedUpperBounds.first(), Endpoint.Exclude));
            }
        }
        return new MakeRange<T>(sequencer, comparator, emptyValue).apply(intersection);
    }

}
