package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.concepts.CompareToBuilder;

/**
 * smallest lower bounds and greatest upper bounds ranges come first
 * @author rferranti
 */
public class RangeComparator<T> implements Comparator<Range<T>>{

    @Override
    public int compare(Range<T> lhs, Range<T> rhs) {
        return new CompareToBuilder().append(lhs.lower(), rhs.lower()).
                append(rhs.upper(), lhs.upper()).
                toComparison();
    }

}
