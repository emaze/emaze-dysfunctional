package net.emaze.dysfunctional.ranges.ips;

import java.util.ArrayList;
import java.util.List;
import net.emaze.dysfunctional.concepts.CompareToBuilder;
import net.emaze.dysfunctional.ranges.DenseRange;
import net.emaze.dysfunctional.ranges.Range;
import net.emaze.dysfunctional.ranges.RangePolicy;

/**
 *
 * @author rferranti
 */
public class IpRangePolicy implements RangePolicy<Integer> {
    @Override
    public List<Range<Integer>> normalize(Range<Integer>... ranges) {
        final List<Range<Integer>> out = new ArrayList<Range<Integer>>();
        //TODO: implement me
        return out;
    }

    @Override
    public boolean contains(DenseRange<Integer> range, Integer element) {
        return element >= range.lower() && element <= range.upper();
    }

    @Override
    public Integer next(Integer element) {
        return element+1;
    }

    @Override
    public int compare(Range<Integer> lhs, Range<Integer> rhs) {
        return new CompareToBuilder().
                append(lhs.lower(), rhs.lower()).
                append(lhs.upper(), lhs.upper()).
                toComparison();
    }
}
