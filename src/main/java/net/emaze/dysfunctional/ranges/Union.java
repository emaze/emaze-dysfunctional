package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * {@code A ∪ B}.
 *
 * @author rferranti
 */
public class Union<T> implements BinaryDelegate<Range<T>, Range<T>, Range<T>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<Maybe<T>> comparator;
    private final T emptyValue;

    public Union(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.emptyValue = emptyValue;
    }

    @Override
    public Range<T> perform(Range<T> lhs, Range<T> rhs) {
        final List<DenseRange<T>> ranges = new ArrayList<DenseRange<T>>();
        ranges.addAll(lhs.densified());
        ranges.addAll(rhs.densified());
        return new MakeRange<T>(sequencer, comparator, emptyValue).perform(ranges);
    }
}
