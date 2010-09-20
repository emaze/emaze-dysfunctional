package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import net.emaze.dysfunctional.iterations.sequencing.SequencingPolicy;
import java.util.List;

/**
 * @author rferranti
 */
public interface RangePolicy<T> extends SequencingPolicy<T>, Comparator<T> {
    public List<DenseRange<T>> asNonOverlapping(DenseRange<T>... ranges);
    public String toString(DenseRange<T> range);
    public String toString(List<DenseRange<T>> ranges);
}
