package net.emaze.dysfunctional.ranges;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.order.CompareToBuilder;

/**
 * smallest lower bounds with greatest upper bounds ranges come first
 * @param <T>
 * @author rferranti
 */
public class RangeComparator<T> implements Comparator<Range<T>>, Serializable {

    private static final long serialVersionUID = 1l;
    
    private final Comparator<Maybe<T>> comparator;

    public RangeComparator(Comparator<T> comparator) {
        this.comparator = new NothingIsGreatestComparator<T>(comparator);
    }

    @Override
    public int compare(Range<T> lhs, Range<T> rhs) {
        return new CompareToBuilder().append(lhs.isEmpty(), rhs.isEmpty()).
                append(lhs.first(), rhs.first()).
                append(rhs.afterLast(), lhs.afterLast(), comparator).
                toComparison();
    }
}

