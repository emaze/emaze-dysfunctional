package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.iterations.sequencing.SequencingPolicy;
import java.util.Comparator;
import java.util.List;

/**
 * @author rferranti
 */
public interface RangePolicy<T extends Comparable<T>> extends SequencingPolicy<T>, Comparator<Range<T>> {
    public List<Range<T>> normalize(Range<T>... ranges);
    public boolean contains(DenseRange<T> range, T element);
}
