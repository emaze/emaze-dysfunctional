package net.emaze.dysfunctional.ranges;

import java.util.*;
import java.util.function.BinaryOperator;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * {@code A ∪ B}.
 *
 * @author rferranti
 */
public class Union<T> implements BinaryOperator<Range<T>> {

    private final SequencingPolicy<T> sequencer;
    private final Comparator<Optional<T>> comparator;
    private final T emptyValue;

    public Union(SequencingPolicy<T> sequencer, Comparator<Optional<T>> comparator, T emptyValue) {
        this.sequencer = sequencer;
        this.comparator = comparator;
        this.emptyValue = emptyValue;
    }

    @Override
    public Range<T> apply(Range<T> lhs, Range<T> rhs) {
        final List<DenseRange<T>> ranges = new ArrayList<>();
        ranges.addAll(lhs.densified());
        ranges.addAll(rhs.densified());
        return new MakeRange<>(sequencer, comparator, emptyValue).apply(ranges);
    }
}
