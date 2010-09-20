package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.concepts.CompareToBuilder;

/**
 *
 * @author rferranti
 */
public class RangeComparator<T> implements Comparator<Range<T>>{

    @Override
    public int compare(Range<T> lhs, Range<T> rhs) {
        return new CompareToBuilder().append(lhs.lower(), rhs.lower()).
                append(lhs.upper(), lhs.upper()).
                toComparison();
    }

}
