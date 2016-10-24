package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.Optional;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;

/**
 *
 * @author rferranti
 */
public class MakeRange<T> implements Function<List<DenseRange<T>>, Range<T>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<Optional<T>> comparator;
    private final DenseRange<T> empty;

    public MakeRange(SequencingPolicy<T> sequencer, Comparator<Optional<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.empty = new DenseRange<T>(sequencer, comparator, Endpoint.Include, emptyValue, Optional.of(emptyValue), Endpoint.Exclude);
    }

    @Override
    public Range<T> apply(List<DenseRange<T>> wannaBeRange) {
        if (wannaBeRange.isEmpty()) {
            return empty;
        }
        if (wannaBeRange.size() == 1) {
            return wannaBeRange.get(0);
        }
        final List<DenseRange<T>> densified = new Densify(sequencer, comparator).apply(wannaBeRange);
        if (densified.size() == 1) {
            return densified.get(0);
        }
        return new SparseRange<T>(sequencer, comparator, densified);
    }
}
