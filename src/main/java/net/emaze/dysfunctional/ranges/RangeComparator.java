package net.emaze.dysfunctional.ranges;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.order.CompareToBuilder;

/**
 * smallest lower bounds with greatest upper bounds ranges come first
 * @param <T>
 * @author rferranti
 */
public class RangeComparator<T> implements Comparator<Range<T>>, Serializable {

    private static final long serialVersionUID = 1l;

    @Override
    public int compare(Range<T> lhs, Range<T> rhs) {
        return new CompareToBuilder().append(lhs.first(), rhs.first()).
                append(rhs.afterLast(), lhs.afterLast()).
                toComparison();
    }
}
