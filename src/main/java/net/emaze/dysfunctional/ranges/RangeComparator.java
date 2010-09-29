package net.emaze.dysfunctional.ranges;

import java.io.Serializable;
import java.util.Comparator;
import net.emaze.dysfunctional.order.CompareToBuilder;

/**
 * smallest lower bounds with greatest upper bounds ranges come first
 * @author rferranti
 */
public class RangeComparator<T> implements Comparator<Range<T>>, Serializable{

    @Override
    public int compare(Range<T> lhs, Range<T> rhs) {
        return new CompareToBuilder().append(lhs.lower(), rhs.lower()).
                append(rhs.upper(), lhs.upper()).
                toComparison();
    }

}
