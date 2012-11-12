package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.SequencingPolicy;

/**
 * {@code A ∆ B} = {@code (A\B) ∪ (B\A)}.
 *
 * @author rferranti
 */
public class SymmetricDifference<T> implements BinaryDelegate<Range<T>, Range<T>, Range<T>> {

    private final Union<T> union;
    private final Difference<T> diff;

    public SymmetricDifference(SequencingPolicy<T> sequencer, Comparator<Maybe<T>> comparator, T emptyValue) {
        union = new Union<T>(sequencer, comparator, emptyValue);
        diff = new Difference<T>(sequencer, comparator, emptyValue);
    }

    @Override
    public Range<T> perform(Range<T> lhs, Range<T> rhs) {
        return union.perform(diff.perform(lhs, rhs), diff.perform(rhs, lhs));
    }
}
