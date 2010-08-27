package net.emaze.disfunctional.ranges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.disfunctional.delegates.Predicate;
import net.emaze.disfunctional.iterations.Iterations;

/**
 *
 * @author rferranti
 */
public class SparseRange<T extends Comparable<T>> implements Range<T> {

    private final RangePolicy<T> policy;
    private final List<Range<T>> ranges = new ArrayList<Range<T>>();

    private List<Range<T>> normalizeRanges(List<Range<T>> ranges) {
        
        return null;
    }

    public SparseRange(RangePolicy<T> policy, Range<T>... ranges) {
        if (ranges.length == 0) {
            throw new IllegalArgumentException("SparseRange<T> must be constructed with at least one argument");
        }
        this.policy = policy;
        this.ranges.addAll(normalizeRanges(Arrays.asList(ranges)));
    }

    public boolean contains(final T element) {
        return Iterations.any(ranges, new Predicate<Range<T>>() {

            public boolean test(Range<T> range) {
                return range.contains(element);
            }
        });
    }

    public T lower() {
        return ranges.get(0).lower();
    }

    public T upper() {
        return ranges.get(ranges.size() - 1).upper();
    }
}
