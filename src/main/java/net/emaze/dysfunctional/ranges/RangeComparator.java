package net.emaze.dysfunctional.ranges;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.CompareToBuilder;

/**
 * smallest lower bounds with greatest upper bounds ranges come first
 *
 * @param <T>
 * @author rferranti
 */
public class RangeComparator<T> implements Comparator<Range<T>>, Serializable {

    private static final long serialVersionUID = 1l;
    private final Comparator<Maybe<T>> comparator;

    public RangeComparator(Comparator<Maybe<T>> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(Range<T> lhs, Range<T> rhs) {
        return new CompareToBuilder().append(!lhs.iterator().hasNext(), !rhs.iterator().hasNext()).
                append(Maybe.just(lhs.begin()), Maybe.just(rhs.begin()), comparator).
                append(rhs.end(), lhs.end(), comparator).
                toComparison();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof RangeComparator == false) {
            return false;
        }
        final RangeComparator<T> other = (RangeComparator<T>) rhs;
        return this.comparator.equals(other.comparator);
    }

    @Override
    public int hashCode() {
        return comparator.hashCode();
    }
}
