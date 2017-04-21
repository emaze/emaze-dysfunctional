package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.ranges.Range.Endpoint;

/**
 * @author rferranti
 */
public class MakeRange<T> implements Delegate<Range<T>, List<DenseRange<T>>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<Maybe<T>> comparator;
    private final DenseRange<T> empty;

    public MakeRange(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.empty = new DenseRange<T>(sequencer, comparator, Endpoint.Include, emptyValue, Maybe.just(emptyValue), Endpoint.Exclude);
    }

    @Override
    public Range<T> perform(List<DenseRange<T>> wannaBeRange) {
        if (wannaBeRange.isEmpty()) {
            return empty;
        }
        if (wannaBeRange.size() == 1) {
            return wannaBeRange.get(0);
        }
        final List<DenseRange<T>> densified = new Densify<T>(sequencer, comparator).perform(wannaBeRange);
        if (densified.size() == 1) {
            return densified.get(0);
        }
        if (densified.isEmpty()) {
            return empty;
        }
        return new SparseRange<T>(sequencer, comparator, densified);
    }
}
