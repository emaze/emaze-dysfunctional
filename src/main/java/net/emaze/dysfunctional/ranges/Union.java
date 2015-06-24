package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        final List<DenseRange<T>> ranges = new ArrayList<DenseRange<T>>();
        ranges.addAll(lhs.densified());
        ranges.addAll(rhs.densified());
        final Densify<T> densifier = new Densify<T>(sequencer, comparator);
        return new MakeRange<T>(sequencer, comparator, emptyValue).apply(densifier.apply(ranges));
    }
}
