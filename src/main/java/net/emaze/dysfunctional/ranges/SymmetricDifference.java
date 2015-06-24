package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.Optional;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * {@code A ∆ B} = {@code (A\B) ∪ (B\A)}.
 *
 * @author rferranti
 */
public class SymmetricDifference<T> implements BinaryOperator<Range<T>> {

    private final Union<T> union;
    private final Difference<T> diff;

    public SymmetricDifference(SequencingPolicy<T> sequencer, Comparator<Optional<T>> comparator, T emptyValue) {
        union = new Union<T>(sequencer, comparator, emptyValue);
        diff = new Difference<T>(sequencer, comparator, emptyValue);
    }

    @Override
    public Range<T> apply(Range<T> lhs, Range<T> rhs) {
        return union.apply(diff.apply(lhs, rhs), diff.apply(rhs, lhs));
    }
}
